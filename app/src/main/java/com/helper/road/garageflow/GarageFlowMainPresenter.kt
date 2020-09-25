package com.helper.road.garageflow

import com.helper.road.garageflow.localdb.entity.Vehicle

interface GarageFlowMainPresenter {
    fun addVehicle(vehicle:Vehicle)
    fun getVehicles():List<Vehicle>
}