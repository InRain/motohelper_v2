package com.helper.moto.login

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.helper.moto.R


class LoginActivity : Activity(), LoginView {

    private lateinit var loginPresenter: LoginPresenter;
    private lateinit var signInButton: Button;
    private lateinit var registerButton: Button;
    private lateinit var usernameEditText: EditText;
    private lateinit var passwordEditText: EditText;

    private val INTERNET_PERMISSION_CODE: Int = 10001
    private val ACCESS_FINE_LOCATION_PERMISSON_CODE: Int = 20002


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (!isPermissionGranted(Manifest.permission.INTERNET)) {
            requestPermission(Manifest.permission.INTERNET, INTERNET_PERMISSION_CODE)
        }
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermission(
                Manifest.permission.ACCESS_FINE_LOCATION,
                ACCESS_FINE_LOCATION_PERMISSON_CODE
            )
        }
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
        passwordEditText = findViewById(R.id.password);

        signInButton.setOnClickListener(View.OnClickListener {
            loginPresenter.doLogin(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        })

    }

    override fun launchUserProfileActivity() {
        TODO("Not yet implemented")
    }

    override fun showMessage(type: String?, message: String?) {
        Log.e("myApp_all", "MESSAGE: Type: ${type} Text: ${message}");
    }

    override fun showLoginFormView(active: Boolean) {
        Log.e("myApp_all", "User logged in");
    }

    override fun getMsgString(resourceId: Int): String? {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: LoginPresenter?) {
        if (presenter != null) {
            loginPresenter = presenter
        };
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        // запрашиваем разрешение
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    }

    private fun isPermissionGranted(permission: String): Boolean {
        val permissionCheck = checkSelfPermission(permission)
        return permissionCheck.equals(PackageManager.PERMISSION_GRANTED)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
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
        } else {
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

        val dialog = alertDialogBuilder.create();
        dialog.show()

    }

    private fun openAppSettings() {
        val intent = Intent()
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.setData(Uri.parse("package:$packageName"))
        startActivityForResult(intent, INTERNET_PERMISSION_CODE)
    }


}