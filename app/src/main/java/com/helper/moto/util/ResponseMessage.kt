package com.helper.moto.util

import com.google.gson.annotations.SerializedName

data class ResponseMessage (
    @SerializedName("message") val message:String,
    @SerializedName("description") val description: String
)