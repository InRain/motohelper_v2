package com.helper.road.register

import com.helper.road.register.model.dto.ApplicationUser

interface RegisterPresenter {
    fun validateUserInput(email: String, phone:String, userName:String, fullName:String, password:String, passwordConfirmation:String):Boolean
    fun registerUser(applicationUser: ApplicationUser)
}