package com.helper.moto.login

import com.helper.moto.util.BasePresenter

interface LoginPresenter : BasePresenter {
    fun doLogin(
        userName: String?,
        password: String?
    )

    fun validateEmail(email: String?): Boolean
    fun validatePassword(password: String?): Boolean
    fun getMessage(code: Any?): String?
}
