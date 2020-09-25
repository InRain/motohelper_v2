package com.helper.road.garageflow.localdb.entity

import java.util.*

data class ServiceOperation(
    val id: Int,
    val vehicleId: Int,
    val description: String,
    val run: Int,
    val cost: Double,
    val date: Date
)