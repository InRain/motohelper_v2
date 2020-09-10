package com.helper.moto.register

import com.helper.moto.register.model.ApplicationUser

class RegisterPresenterImplementation(private val registerView: RegisterView) : RegisterPresenter {
    override fun registerUser(applicationUser: ApplicationUser) {
        TODO("Not yet implemented")
    }

    override fun cancelRegistration() {
        TODO("Not yet implemented")
    }

    override fun start() {
        registerView.initializeUI()
    }
}