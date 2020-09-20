package com.helper.moto.basecomponents

import com.google.gson.annotations.SerializedName

data class ResponseMessage (
    @SerializedName("message") val message:String,
    @SerializedName("description") val description: String
)