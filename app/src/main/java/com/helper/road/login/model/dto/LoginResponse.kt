package com.helper.road.login.model.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse (
     @SerializedName("username") val userName :String,
     @SerializedName("token") val token :String
)