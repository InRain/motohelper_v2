package com.helper.moto.login

import com.helper.moto.login.model.LoginModel

class LoginPresenterImpl(
    private val loginView: LoginView
) : LoginPresenter {
    private val loginModel = LoginModel()

    override fun doLogin(userName: String?, password: String?) {
        if (userName != null && password != null) {
            val loginData = loginModel.doLogin(userName, password)
            if (loginData != "FORBIDDEN") {
                loginView.launchMainApplication()
            }
        }
    }

    override fun validateEmail(email: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun validatePassword(password: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMessage(code: Any?): String? {
        TODO("Not yet implemented")
    }

    override fun start() {
        loginView.initializeUI();
    }
}