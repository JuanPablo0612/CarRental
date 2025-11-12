package com.juanpablo0612.carrental.domain.model

data class Rental(
    val id: String,
    val vehicleId: String,
    val userId: String,
    val startDate: String,
    val endDate: String,
    val totalPrice: Double,
)
