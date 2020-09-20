package com.helper.moto.register

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.helper.moto.R
import com.helper.moto.register.model.dto.ApplicationUser
import com.helper.moto.register.model.dto.Role
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : Activity(), RegisterView {


    private lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPresenter = RegisterPresenterImplementation(this)
        setContentView(R.layout.activity_register)
        initializeUI()
    }

    override fun showProgressBar(active: Boolean) {
        if (active) {
            progressLayout.visibility = View.VISIBLE
        }
        if (!active) {
            progressLayout.visibility = View.GONE
        }
    }

    fun initializeUI() {
        val inputClickListener = View.OnFocusChangeListener { v, hasFocus ->
            v.background.setTint(resources.getColor(R.color.colorGrey))
        }

        registerButton.setOnClickListener {
            if (registerPresenter.validateUserInput(
                    emailEditText.text.toString(),
                    phoneEditText.text.toString(),
                    userNameEditText.text.toString(),
                    fullNameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    confirmPasswordEditText.text.toString()
                )
            ) {
                registerPresenter.registerUser(
                    ApplicationUser(
                        userNameEditText.text.toString(),
                        fullNameEditText.text.toString(),
                        phoneEditText.text.toString(),
                        emailEditText.text.toString(),
                        passwordEditText.text.toString(),
                        arrayListOf(Role("USER"))
                    )
                )
            } else {
                Toast.makeText(this, "Input isnt valid", Toast.LENGTH_SHORT).show()
            }
        }
        cancelButton.setOnClickListener{
            finish()
        }
    }


    override fun showMessage(type: String?, message: String?) {
        Toast.makeText(this, "$type : $message", Toast.LENGTH_SHORT).show()
    }

    override fun showSuccessDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(resources.getString(R.string.welcome))
            .setMessage(resources.getString(R.string.welcome_dialog_message))
            .setPositiveButton(resources.getText(R.string.ok)) { dialog, which ->
                dialog.cancel()
                finish()
            }
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }

    override fun showEmailEmpty() {
        emailEditText.background.setTint(resources.getColor(R.color.colorRed))
    }

    override fun showPhoneEmpty() {
        phoneEditText.background.setTint(resources.getColor(R.color.colorRed))
    }

    override fun showUserNameEmpty() {
        userNameEditText.background.setTint(resources.getColor(R.color.colorRed))
    }

    override fun showFullNameEmpty() {
        fullNameEditText.background.setTint(resources.getColor(R.color.colorRed))
    }

    override fun showPasswordEmpty() {
        passwordEditText.background.setTint(resources.getColor(R.color.colorRed))
    }

    override fun showPasswordConfirmEmpty() {
        confirmPasswordEditText.background.setTint(resources.getColor(R.color.colorRed))
    }

    override fun showPasswordsAreNotEquals() {
        errorMessageTextView.visibility = View.VISIBLE
        errorMessageTextView.text = resources.getText(R.string.passwords_are_not_equals)
        errorMessageTextView.setTextColor(resources.getColor(R.color.colorRed))
        passwordEditText.background.setTint(resources.getColor(R.color.colorRed))
        confirmPasswordEditText.background.setTint(resources.getColor(R.color.colorRed))
    }

    override fun setPresenter(presenter: RegisterPresenter?) {
        if (presenter != null) {
            registerPresenter = presenter
        }
    }
}