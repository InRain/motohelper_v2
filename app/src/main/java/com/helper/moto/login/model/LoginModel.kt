package com.helper.moto.login.model

import android.os.AsyncTask
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.OutputStreamWriter
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class LoginModel : LoginDataSource {
    override fun doLogin(login: String, password: String): String {
        val credentials = mapOf("login" to login, "password" to password)
        val asyncLoginRequest = AsyncLoginRequest()
        val resp = asyncLoginRequest.execute(credentials)
        return resp.get()
    }

    class AsyncLoginRequest : AsyncTask<Map<String, String>, Int, String>() {
        override fun doInBackground(vararg params: Map<String, String>?): String {
            val login = params[0]?.get("login")
            val password = params[0]?.get("password")
            val mURL = URL("http://192.168.0.103:8080/api/auth/login")

            try {
                with(mURL.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"
                    doOutput = true
                    doInput = true
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("User-Agent", "PostmanRuntime/7.26.3")
                    setRequestProperty("Accept", "*/*")
                    setRequestProperty("Connection", "keep-alive")
                    val writer = OutputStreamWriter(outputStream)

                    val gson = Gson()
                    var loginRequestBody = ""
                    if (login != null && password != null) {
                        loginRequestBody = gson.toJson(LoginRequest(login, password))
                    }
                    writer.write(loginRequestBody)
                    writer.close()

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        val respBody = inputStream.bufferedReader().use(BufferedReader::readText)
                        val response = gson.fromJson(respBody, LoginResponse::class.java)
                        return response.token
                    }
                    return if (responseCode == HttpsURLConnection.HTTP_FORBIDDEN) {
                        LoginStatus.FORBIDDEN.name
                    } else {
                        LoginStatus.INTERNAL_ERROR.name
                    }
                }

            } catch (connectException: ConnectException) {
                return LoginStatus.UNABLE_CONNECT.name
            }
        }
    }
}