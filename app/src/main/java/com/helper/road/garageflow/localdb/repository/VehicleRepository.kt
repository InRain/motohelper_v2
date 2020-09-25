package com.helper.road.garageflow.localdb.repository

import android.content.ContentValues
import android.content.Context
import com.helper.road.garageflow.localdb.GarageDbHelper
import com.helper.road.garageflow.localdb.entity.Vehicle
import com.helper.road.garageflow.localdb.entity.VehicleType

class VehicleRepository(private val ctx: Context) {
    private val database = GarageDbHelper(ctx).writableDatabase

    fun getVehicles(): List<Vehicle> {
        val cursor =
            database.query(GarageDbHelper.VEHICLES_TABLE_NAME, null, null, null, null, null, null)
        val vehiclesList = mutableListOf<Vehicle>()
        with(cursor) {
            moveToFirst()
            if (count > 0) {
                moveToFirst()
                do {
                    vehiclesList.add(
                        Vehicle(
                            getInt(0),
                            getString(1),
                            getInt(2),
                            getInt(3),
                            getString(4),
                            VehicleType.values()[getInt(5)]
                        )
                    )
                } while (moveToNext())
            }
        }
        return vehiclesList
    }

    fun addVehicle(vehicle: Vehicle){
        with(ContentValues()){
            put(GarageDbHelper.VEHICLE_NAME, vehicle.Name)
            put(GarageDbHelper.VEHICLE_INITIAL_RUN, vehicle.InitialRun)
            put(GarageDbHelper.VEHICLE_CURRENT_RUN, vehicle.CurrentRun)
            put(GarageDbHelper.VEHICLE_PHOTO_PATH, vehicle.photoPath)
            put(GarageDbHelper.VEHICLE_TYPE, vehicle.type.ordinal)
            database.insert(GarageDbHelper.VEHICLES_TABLE_NAME,null, this)
        }
    }
}