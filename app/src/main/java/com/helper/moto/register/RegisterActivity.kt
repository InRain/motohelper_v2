package com.helper.moto.register

import android.app.Activity
import android.os.Bundle
import com.helper.moto.R


class RegisterActivity : Activity() , RegisterView {

    private lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPresenter = RegisterPresenterImplementation(this)
        setContentView(R.layout.activity_register)

    }

    override fun showProgressBar(active: Boolean) {
        TODO("Not yet implemented")
    }

    override fun initializeUI() {
        TODO("Not yet implemented")
    }

    override fun launchMainApplication() {
        TODO("Not yet implemented")
    }

    override fun showMessage(type: String?, message: String?) {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: RegisterPresenter?) {
        if(presenter !=null){
            registerPresenter = presenter
        }
    }
}