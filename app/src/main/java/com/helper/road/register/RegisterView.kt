package com.helper.road.register

interface RegisterView {
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