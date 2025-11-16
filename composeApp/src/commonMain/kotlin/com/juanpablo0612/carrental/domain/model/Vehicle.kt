package com.juanpablo0612.carrental.domain.model

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