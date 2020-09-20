package com.helper.moto.register.model

import com.helper.moto.register.model.dto.ApplicationUser
import com.helper.moto.register.model.dto.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationRepository {
    @POST("/api/registration/")
    fun doRegistration(@Body applicationUser: ApplicationUser) : Call<RegistrationResponse>
}