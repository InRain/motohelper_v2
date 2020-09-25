package com.helper.road.login

interface LoginView {

    fun showProgressBar(active: Boolean)
    fun launchMainApplication()
    fun showMessage(type: String?, message: String?)
    fun showRegisterScreen()
}