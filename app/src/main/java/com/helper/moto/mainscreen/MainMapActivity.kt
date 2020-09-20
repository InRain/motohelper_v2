package com.helper.moto.mainscreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.helper.moto.R


class MainMapActivity : MainMapPresenter ,AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_map)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = resources.getText(R.string.app_name)
        setSupportActionBar(toolbar)
        val drawer =
            findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.accept, R.string.cancel
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

}