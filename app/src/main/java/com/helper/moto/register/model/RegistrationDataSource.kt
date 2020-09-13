package com.helper.moto.register.model

interface RegistrationDataSource {
    fun registerUser(user: ApplicationUser):RegistrationStatus
}