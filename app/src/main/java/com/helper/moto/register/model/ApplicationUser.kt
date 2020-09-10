package com.helper.moto.register.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApplicationUser (
    @SerializedName("userName") val userName:String,
    @SerializedName("firstName") val firstName:String,
    @SerializedName("phone") val phone:String,
    @SerializedName("email") val email:String,
    @SerializedName("birthDate") val birthDate:Date,
    @SerializedName("password") val password:Date,
    @SerializedName("roles") val roles:List<Role>
)