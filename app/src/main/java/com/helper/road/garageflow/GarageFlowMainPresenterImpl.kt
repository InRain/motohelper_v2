package com.helper.road.garageflow

import android.content.Context
import com.helper.road.garageflow.localdb.entity.Vehicle
import com.helper.road.garageflow.localdb.repository.VehicleRepository

class GarageFlowMainPresenterImpl(val context: Context): GarageFlowMainPresenter {
    private val vehicleRepository = VehicleRepository(context)

    override fun addVehicle(vehicle: Vehicle) {
        vehicleRepository.addVehicle(vehicle)
    }

    override fun getVehicles(): List<Vehicle> {
        return vehicleRepository.getVehicles()
    }
}