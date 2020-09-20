package com.helper.moto.register

import com.helper.moto.basecomponents.BaseView

interface RegisterView : BaseView<RegisterPresenter?>{
    fun showProgressBar(active: Boolean)
    fun showMessage(type: String?, message: String?)
    fun showSuccessDialog()
    fun showEmailEmpty()
    fun showPhoneEmpty()
    fun showUserNameEmpty()
    fun showFullNameEmpty()
    fun showPasswordEmpty()
    fun showPasswordConfirmEmpty()
    fun showPasswordsAreNotEquals()
}