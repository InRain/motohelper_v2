package com.helper.moto.register.model.dto

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("message") val message: String,
    @SerializedName("description") val description: String
)