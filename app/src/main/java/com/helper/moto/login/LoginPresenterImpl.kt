package com.helper.moto.login

import com.helper.moto.login.model.LoginModel
import com.helper.moto.login.model.LoginStatus

class LoginPresenterImpl(
    private val loginView: LoginView
) : LoginPresenter {
    private val loginModel = LoginModel()

    override fun doLogin(userName: String?, password: String?) {
        if (userName != null && password != null) {
            loginView.showProgressBar(true);
            when (loginModel.doLogin(userName, password)) {
                LoginStatus.UNABLE_CONNECT.name -> {
                    loginView.showMessage("Error", "Something wrong with connection")
                    loginView.showProgressBar(false);
                    return
                }
                LoginStatus.INTERNAL_ERROR.name -> {
                    loginView.showMessage("Error", "Something wrong on server")
                    loginView.showProgressBar(false);
                    return
                }
                LoginStatus.FORBIDDEN.name -> {
                    loginView.showMessage("Error", "Wrong Credentials")
                    loginView.showProgressBar(false);
                    return
                }
            }
            loginView.showProgressBar(false);
            loginView.launchMainApplication()
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

    override fun showRegisterScreen() {
        loginView.showRegisterScreen()
    }

    override fun start() {
        loginView.initializeUI();
    }
}