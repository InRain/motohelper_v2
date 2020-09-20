package com.helper.moto.register


import com.google.gson.Gson
import com.helper.moto.register.model.RegistrationService
import com.helper.moto.register.model.dto.ApplicationUser
import com.helper.moto.register.model.dto.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterPresenterImplementation(
    private val registerView: RegisterView
) : RegisterPresenter {

    override fun validateUserInput(
        email: String,
        phone: String,
        userName: String,
        fullName: String,
        password: String,
        passwordConfirmation: String
    ): Boolean {

        var check = true

        if (email.isEmpty()) {
            registerView.showEmailEmpty()
            check = false
        }
        if (phone.isEmpty()) {
            registerView.showPhoneEmpty()
            check = false
        }
        if (userName.isEmpty()) {
            registerView.showUserNameEmpty()
            check = false
        }
        if (fullName.isEmpty()) {
            registerView.showFullNameEmpty()
            check = false
        }
        if (password.isEmpty()) {
            registerView.showPasswordEmpty()
            check = false
        }
        if (passwordConfirmation.isEmpty()) {
            registerView.showPasswordConfirmEmpty()
            check = false
        }
        if (!password.equals(passwordConfirmation)) {
            registerView.showPasswordsAreNotEquals()
            check = false
        }

        return check

    }


    override fun registerUser(applicationUser: ApplicationUser) {

        val registrationService = RegistrationService(object : Callback<RegistrationResponse> {

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                registerView.showMessage("ERROR", "REQUEST FAILED")
            }

            override fun onResponse(
                call: Call<RegistrationResponse>,
                response: Response<RegistrationResponse>
            ) {
                if (response.isSuccessful) {
                    registerView.showSuccessDialog()

                } else {
                    val registrationResponse = Gson().fromJson<RegistrationResponse>(
                        response.errorBody()?.string(),
                        RegistrationResponse::class.java
                    )

                    when (registrationResponse.message) {
                        "EXISTS" -> registerView.showMessage(
                            "ERROR",
                            "USER EXISTS"
                        )
                    }
                }
            }

        })
        registrationService.registerUser(applicationUser)
    }


//    fun onAsyncTaskCompleted(registrationStatus: RegistrationStatus) {
//        registerView.showProgressBar(false)
//        if (registrationStatus == RegistrationStatus.REGISTERED) {
//            registerView.showMessage("INFO", "USER REGISTERED")
//            registerView.showSuccessDialog()
//        }
//        if (registrationStatus == RegistrationStatus.USER_EXISTS) {
//            registerView.showMessage("ERROR", "USER EXISTS")
//        }
//        if (registrationStatus == RegistrationStatus.INTERNAL_ERROR) {
//            registerView.showMessage("ERROR", "INTERNAL ERROR")
//        }
//    }


}