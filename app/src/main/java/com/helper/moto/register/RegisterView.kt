package com.helper.moto.register

import com.helper.moto.util.BaseView

interface RegisterView : BaseView<RegisterPresenter?>{
    fun showProgressBar(active: Boolean)
    fun initializeUI()
    fun launchMainApplication()
    fun showMessage(type: String?, message: String?)
}