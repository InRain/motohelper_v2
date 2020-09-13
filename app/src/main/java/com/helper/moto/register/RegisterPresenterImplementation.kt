package com.helper.moto.register

import android.os.AsyncTask
import com.helper.moto.register.model.ApplicationUser
import com.helper.moto.register.model.RegistrationModel
import com.helper.moto.register.model.RegistrationStatus
import com.helper.moto.util.OnRegistrationTaskCompleted
import java.util.concurrent.TimeUnit

class RegisterPresenterImplementation(
    private val registerView: RegisterView
) : RegisterPresenter, OnRegistrationTaskCompleted {

    val registrationModel = RegistrationModel()
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

        class AsyncUserRegistrationRequest(val onRegistrationTaskCompleted: OnRegistrationTaskCompleted) : AsyncTask<ApplicationUser, Int, RegistrationStatus>() {

            override fun onPreExecute() {
                super.onPreExecute()
                registerView.showProgressBar(true)

            }

            override fun doInBackground(vararg params: ApplicationUser?): RegistrationStatus {
                val userForRegistration = params[0]
                if (userForRegistration != null) {
                    return registrationModel.registerUser(userForRegistration)
                }
                return RegistrationStatus.INTERNAL_ERROR
            }

            override fun onPostExecute(result: RegistrationStatus?) {
                super.onPostExecute(result)
                if(result != null) {
                    onRegistrationTaskCompleted.onAsyncTaskCompleted(result)
                }
            }
        }

        val asyncUserRegistration = AsyncUserRegistrationRequest(this)
        asyncUserRegistration.execute(applicationUser)
    }

    override fun cancelRegistration() {
        TODO("Not yet implemented")
    }

    override fun start() {
        registerView.initializeUI()
    }

    override fun onAsyncTaskCompleted(registrationStatus: RegistrationStatus) {
        registerView.showProgressBar(false)
        if (registrationStatus == RegistrationStatus.REGISTERED) {
            registerView.showMessage("INFO", "USER REGISTERED")
            registerView.showSuccessDialog()
        }
        if (registrationStatus == RegistrationStatus.USER_EXISTS) {
            registerView.showMessage("ERROR", "USER EXISTS")
        }
        if (registrationStatus == RegistrationStatus.INTERNAL_ERROR) {
            registerView.showMessage("ERROR", "INTERNAL ERROR")
        }
    }


}