package com.helper.moto.login


interface LoginPresenter {
    fun doLogin(
        userName: String?,
        password: String?
    )
    fun showRegisterScreen()
}
