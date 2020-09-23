package com.helper.moto.mainscreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.helper.moto.R

class MainMapActivity : MainMapPresenter, AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var map: GoogleMap


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

        val frameLayout = FrameLayout(this)
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun checkGpsPermissions(): Boolean {
        val fineLocation = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarseLocation = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return fineLocation && coarseLocation

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            googleMap.isIndoorEnabled = true
            googleMap.isBuildingsEnabled = true
            googleMap.uiSettings.isCompassEnabled = true
            googleMap.uiSettings.isMapToolbarEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = true
            googleMap.uiSettings.isZoomControlsEnabled = true

            googleMap.setOnMapLongClickListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

            if (checkGpsPermissions()) {
                googleMap.isMyLocationEnabled = true
            }
        }


        map = googleMap!!
        map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(43.1, -87.9)))
    }

}