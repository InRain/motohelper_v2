package com.helper.road.garageflow.localdb.entity

data class Vehicle(
    val id: Int = 0,
    val Name: String,
    val InitialRun: Int,
    val CurrentRun: Int,
    val photoPath: String,
    val type: VehicleType
)