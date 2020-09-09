package com.helper.moto.login.model

interface LoginDataSource {

    fun doLogin(login: String, password: String): String;

}