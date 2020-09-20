package com.helper.moto.login

import com.helper.moto.basecomponents.BaseView

interface LoginView : BaseView<LoginPresenter?> {

    fun showProgressBar(active: Boolean)
    fun launchMainApplication()
    fun showMessage(type: String?, message: String?)
    fun showRegisterScreen()
}