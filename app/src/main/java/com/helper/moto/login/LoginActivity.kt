package com.helper.moto.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.helper.moto.R

class LoginActivity : AppCompatActivity() , LoginView{

    private lateinit var loginPresenter: LoginPresenter;
    private lateinit var signInButton: Button;
    private lateinit var registerButton: Button;
    private lateinit var usernameEditText: EditText;
    private lateinit var passwordEditText: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenterImpl(this);
        loginPresenter.start();
    }

    override fun showProgressBar(active: Boolean) {
        TODO("Not yet implemented")
    }

    override fun initializeUI() {
        signInButton = findViewById(R.id.signIn);
        registerButton = findViewById(R.id.register);
        usernameEditText = findViewById(R.id.username);
        passwordEditText=findViewById(R.id.password);
    }

    override fun launchUserProfileActivity() {
        TODO("Not yet implemented")
    }

    override fun showMessage(type: String?, message: String?) {
        TODO("Not yet implemented")
    }

    override fun showLoginFormView(active: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getMsgString(resourceId: Int): String? {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: LoginPresenter?) {
        if (presenter != null) {
            loginPresenter = presenter
        };
    }
}