package com.helper.road

import android.app.Application

class MotoHelperApplication : Application() {
    private lateinit var token: String

    fun setToken(token: String) {
        this.token = token
    }

    fun getToken(): String {
        return token
    }
}