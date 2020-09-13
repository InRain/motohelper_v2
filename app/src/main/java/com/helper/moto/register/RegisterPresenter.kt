package com.helper.moto.register

import com.helper.moto.register.model.ApplicationUser
import com.helper.moto.util.BasePresenter

interface RegisterPresenter : BasePresenter{
    fun validateUserInput(email: String, phone:String, userName:String, fullName:String, password:String, passwordConfirmation:String):Boolean
    fun registerUser(applicationUser: ApplicationUser)
    fun cancelRegistration()
}