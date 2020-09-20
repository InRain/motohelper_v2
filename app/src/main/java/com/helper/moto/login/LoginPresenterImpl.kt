package com.helper.moto.login

import com.helper.moto.login.model.LoginService
import com.helper.moto.login.model.dto.LoginRequest
import com.helper.moto.login.model.dto.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenterImpl(
    private val loginView: LoginView
) : LoginPresenter {

    override fun doLogin(userName: String?, password: String?) {

        val loginService = LoginService(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginView.showMessage("ERROR", "Smth goes wrong")
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginView.launchMainApplication()
                } else {
                    loginView.showMessage("ERROR", "Wrong credentials was provided")
                }
            }
        })

        if (userName != null && password != null) {
            loginService.loginUser(LoginRequest(userName, password))
        }
    }

    override fun showRegisterScreen() {
        loginView.showRegisterScreen()
    }

}