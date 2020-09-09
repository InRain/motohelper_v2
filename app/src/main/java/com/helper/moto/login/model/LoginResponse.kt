package com.helper.moto.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("username") val userName :String,
    @SerializedName("token") val token :String
)