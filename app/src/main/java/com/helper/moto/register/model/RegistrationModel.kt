package com.helper.moto.register.model

import com.google.gson.Gson
import com.helper.moto.login.model.LoginRequest
import com.helper.moto.login.model.LoginResponse
import com.helper.moto.login.model.LoginStatus
import com.helper.moto.util.ResponseMessage
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class RegistrationModel : RegistrationDataSource {
    override fun registerUser(user: ApplicationUser): RegistrationStatus {

        val mURL = URL("http://192.168.0.103:8080/api/registration/")

        try {
            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                doOutput = true
                doInput = true
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("User-Agent", "PostmanRuntime/7.26.3")
                setRequestProperty("Accept", "*/*")
                setRequestProperty("Connection", "keep-alive")

                val gson = Gson()
                var userString = gson.toJson(user)

                val writer = OutputStreamWriter(outputStream)
                writer.write(userString)
                writer.close()

                if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                    return RegistrationStatus.REGISTERED
                }
                val br =  BufferedReader(InputStreamReader(errorStream));
                val responseText= StringBuilder()
                br.lines().forEach {line -> responseText.append(line)}
                val responseMessage = gson.fromJson(responseText.toString(), ResponseMessage::class.java)

                if(responseMessage.message == "EXISTS"){
                    return RegistrationStatus.USER_EXISTS
                }else{
                    return RegistrationStatus.INTERNAL_ERROR
                }

            }

        } catch (connectException: ConnectException) {
            return RegistrationStatus.INTERNAL_ERROR
        }
    }
}