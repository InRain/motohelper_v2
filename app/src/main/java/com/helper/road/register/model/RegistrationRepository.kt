package com.helper.road.register.model

import com.helper.road.register.model.dto.ApplicationUser
import com.helper.road.register.model.dto.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationRepository {
    @POST("/mhserver/api/registration/")
    fun doRegistration(@Body applicationUser: ApplicationUser) : Call<RegistrationResponse>
}