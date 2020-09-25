package com.helper.road.login.model

import com.helper.road.login.model.dto.LoginRequest
import com.helper.road.login.model.dto.LoginResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginService(val responseCallback: Callback<LoginResponse>) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.103:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val loginRepository: LoginRepository = retrofit.create(LoginRepository::class.java)

    fun loginUser(loginRequest: LoginRequest) {
        loginRepository.doLogin(loginRequest).enqueue(responseCallback)
    }
}