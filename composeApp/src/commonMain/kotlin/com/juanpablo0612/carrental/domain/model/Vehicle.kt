package com.juanpablo0612.carrental.domain.model

import com.juanpablo0612.carrental.data.vehicles.model.VehicleModel

data class Vehicle(
    val id: String,
    val make: String,
    val model: String,
    val year: Int,
    val type: String,
    val pricePerDay: Double,
    val imageUrl: String,
    val isAvailable: Boolean = true,
)

fun Vehicle.toModel() = VehicleModel(
    id = id,
    make = make,
    model = model,
    year = year,
    type = type,
    pricePerDay = pricePerDay,
    imageUrl = imageUrl,
    isAvailable = isAvailable
)