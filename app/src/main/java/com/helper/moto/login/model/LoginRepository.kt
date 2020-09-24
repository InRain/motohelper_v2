package com.helper.moto.login.model

import com.helper.moto.login.model.dto.LoginRequest
import com.helper.moto.login.model.dto.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRepository {
    @POST("/mhserver/api/auth/login")
    fun doLogin(
        @Body loginRequestDTO: LoginRequest
    ) : Call<LoginResponse>
}