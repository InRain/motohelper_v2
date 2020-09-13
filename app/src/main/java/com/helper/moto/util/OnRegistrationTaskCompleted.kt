package com.helper.moto.util

import com.helper.moto.register.model.RegistrationStatus

interface OnRegistrationTaskCompleted {
    fun onAsyncTaskCompleted(registrationStatus: RegistrationStatus)
}