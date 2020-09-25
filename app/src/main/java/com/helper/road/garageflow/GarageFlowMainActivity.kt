package com.helper.road.garageflow

import VehicleTypeAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.helper.road.R
import com.helper.road.garageflow.adapters.VehicleListAdapter
import com.helper.road.garageflow.localdb.entity.Vehicle
import com.helper.road.garageflow.localdb.entity.VehicleType
import kotlinx.android.synthetic.main.activity_garage_flow_main.*
import kotlinx.android.synthetic.main.content_garage_flow_main.*
import kotlinx.android.synthetic.main.dialog_vehicle_add.*
import kotlinx.android.synthetic.main.dialog_vehicle_add.view.*

class GarageFlowMainActivity : AppCompatActivity(), GarageFlowMainView {

    private lateinit var presenter: GarageFlowMainPresenter
    private lateinit var vehicleListAdapter: VehicleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garage_flow_main)
        setSupportActionBar(garageFlowToolbar)
        garageFlowToolbar.title = resources.getString(R.string.my_garage)
        presenter = GarageFlowMainPresenterImpl(this)

        addVehicleFab.setOnClickListener { showAddVehicleDialog() }

        vehicleListAdapter = VehicleListAdapter(presenter.getVehicles(), this, R.layout.single_vehicle)
        vehicleListView.adapter = vehicleListAdapter

        vehicleListView.setOnItemClickListener { _, view, position, id ->
            Toast.makeText(
                this,
                vehicleListAdapter.getItem(position).toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showAddVehicleDialog() {

        val addVehicleDialogBuilder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_vehicle_add, addVehicleRoot)

        val vehicleAdapter = VehicleTypeAdapter(this, R.layout.vehicle_type_row)
        dialogLayout.vehicleTypeSpinner.adapter = vehicleAdapter



        addVehicleDialogBuilder.setTitle(R.string.add_vehicle_title)
        addVehicleDialogBuilder.setPositiveButton(
            R.string.add_text
        ) { dialog, which ->
            val currentType = dialogLayout.vehicleTypeSpinner.selectedItem as VehicleType
            val vehicle = Vehicle(
                0,
                dialogLayout.vehicleNameEditText.text.toString(),
                dialogLayout.vehicleRunEditText.text.toString().toInt(),
                dialogLayout.vehicleRunEditText.text.toString().toInt(),
                "",
                currentType
            )
            presenter.addVehicle(vehicle)
            vehicleListAdapter.clear()
            vehicleListAdapter.addAll(presenter.getVehicles())
            dialog.dismiss()
        }

        addVehicleDialogBuilder.setView(dialogLayout)
        addVehicleDialogBuilder.create()
        addVehicleDialogBuilder.show()
    }
}