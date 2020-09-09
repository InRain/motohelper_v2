package com.helper.moto.login

import com.helper.moto.util.BaseView

interface LoginView : BaseView<LoginPresenter?> {

    fun showProgressBar(active: Boolean)
    fun initializeUI()
    fun launchUserProfileActivity()
    fun showMessage(type: String?, message: String?)
    fun showLoginFormView(active: Boolean)
    fun getMsgString(resourceId: Int): String?
}