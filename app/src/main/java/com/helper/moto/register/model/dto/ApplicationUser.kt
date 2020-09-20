package com.helper.moto.register.model.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApplicationUser (
    @SerializedName("userName") val userName:String,
    @SerializedName("firstName") val firstName:String,
    @SerializedName("phone") val phone:String,
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String,
    @SerializedName("roles") val roles:ArrayList<Role>
)