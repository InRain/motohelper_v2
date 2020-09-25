package com.helper.road.login

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.helper.road.R
import com.helper.road.mainscreen.MainMapActivity
import com.helper.road.register.RegisterActivity
import com.helper.road.basecomponents.PreferencesName
import kotlinx.android.synthetic.main.activity_login.*

const val LOGIN_KEY: String = "LOGIN"
const val PASSWORD_KEY: String = "PASSWORD"

class LoginActivity : Activity(), LoginView {

    private lateinit var loginPresenter: LoginPresenter

    private val INTERNET_PERMISSION_CODE: Int = 10001
    private val ACCESS_FINE_LOCATION_PERMISSON_CODE: Int = 20002
    private val WRITE_EXTERNAL_STORAGE_PERMISSION_CODE: Int = 30003
    private val READ_EXTERNAL_STORAGE_PERMISSION_CODE: Int = 40004

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializeUI()
        sharedPreferences = getSharedPreferences(PreferencesName.AUTH.name, Context.MODE_PRIVATE)

        if (!isPermissionGranted(Manifest.permission.INTERNET)) {
            requestPermission(Manifest.permission.INTERNET, INTERNET_PERMISSION_CODE)
        }
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermission(
                Manifest.permission.ACCESS_FINE_LOCATION,
                ACCESS_FINE_LOCATION_PERMISSON_CODE
            )
        }
        if (!isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE_PERMISSION_CODE
            )
        }
        if (!isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE_PERMISSION_CODE
            )
        }
        loginPresenter = LoginPresenterImpl(this)
        val login = sharedPreferences.getString(LOGIN_KEY, null)
        val password = sharedPreferences.getString(PASSWORD_KEY, null)

    }

    override fun showProgressBar(active: Boolean) {
        if (active) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun initializeUI() {
        signInButton.setOnClickListener {
            if (rememberMeCheckBox.isChecked) {
                val editor = sharedPreferences.edit()
                editor.putString(LOGIN_KEY, userNameEditText.text.toString())
                editor.putString(PASSWORD_KEY, passwordEditText.text.toString())
                editor.apply()
            }
            loginPresenter.doLogin(
                userNameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        registerButton.setOnClickListener {
            loginPresenter.showRegisterScreen()
        }

    }

    override fun launchMainApplication() {
        val mainAppIntent = Intent(this, MainMapActivity::class.java)
        startActivity(mainAppIntent)
        finish()
    }

    override fun showMessage(type: String?, message: String?) {
        Log.e("myApp_all", "MESSAGE: Type: $type Text: $message")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showRegisterScreen() {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerIntent);
    }


    private fun requestPermission(permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    }

    private fun isPermissionGranted(permission: String): Boolean {
        val permissionCheck = checkSelfPermission(permission)
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.isEmpty()){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        if (requestCode == INTERNET_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                showPermissionDialog(this)
            }
        } else if (requestCode == ACCESS_FINE_LOCATION_PERMISSON_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                showPermissionDialog(this)
            }
        } else if(requestCode == READ_EXTERNAL_STORAGE_PERMISSION_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                showPermissionDialog(this)
            }
        } else if(requestCode == WRITE_EXTERNAL_STORAGE_PERMISSION_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                showPermissionDialog(this)
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun showPermissionDialog(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(resources.getString(R.string.app_name))
        alertDialogBuilder.setMessage(resources.getString(R.string.permission_request_dialog_message_text))

        val positiveText =
            resources.getString(R.string.permission_request_dialog_positive_button_text)
        val negativeText =
            resources.getString(R.string.permission_request_dialog_negative_button_text)

        alertDialogBuilder.setPositiveButton(positiveText) { _, _ -> openAppSettings() }
        alertDialogBuilder.setNegativeButton(negativeText) { _, _ -> finish() }

        val dialog = alertDialogBuilder.create()
        dialog.show()
    }

    private fun openAppSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.parse("package:$packageName")
        startActivityForResult(intent, INTERNET_PERMISSION_CODE)
    }
}