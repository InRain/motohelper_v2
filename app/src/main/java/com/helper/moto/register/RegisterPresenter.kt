package com.helper.moto.register

import com.helper.moto.register.model.ApplicationUser
import com.helper.moto.util.BasePresenter

interface RegisterPresenter : BasePresenter{
    fun registerUser(applicationUser: ApplicationUser)
    fun cancelRegistration()
}