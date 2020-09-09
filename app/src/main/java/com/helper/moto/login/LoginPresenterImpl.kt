package com.helper.moto.login

class LoginPresenterImpl(
    private val loginView: LoginView
) : LoginPresenter {



    override fun doLogin(userName: String?, password: String?) {
        if(userName.equals("user") && password.equals("password")){
            loginView.showLoginFormView(true)
        } else{
            loginView.showMessage("error","Invalid username or password")
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