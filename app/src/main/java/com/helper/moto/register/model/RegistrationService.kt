package com.helper.moto.register.model
import com.helper.moto.register.model.dto.ApplicationUser
import com.helper.moto.register.model.dto.RegistrationResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationService(private val responseCallBack:Callback<RegistrationResponse>) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.103:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val registrationRepository = retrofit.create(RegistrationRepository::class.java)

    fun registerUser(applicationUser: ApplicationUser){
        registrationRepository.doRegistration(applicationUser).enqueue(responseCallBack)
    }
}