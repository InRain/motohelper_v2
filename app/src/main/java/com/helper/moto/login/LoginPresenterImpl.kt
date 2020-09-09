package com.helper.moto.login

class LoginPresenterImpl(val loginView: LoginView) : LoginPresenter {



    override fun doLogin(userName: String?, password: String?) {
        TODO("Not yet implemented")
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