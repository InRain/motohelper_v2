package com.helper.road.login


interface LoginPresenter {
    fun doLogin(
        userName: String?,
        password: String?
    )
    fun showRegisterScreen()
}
