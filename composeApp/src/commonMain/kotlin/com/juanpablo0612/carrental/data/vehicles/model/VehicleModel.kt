package com.juanpablo0612.carrental.data.vehicles.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleModel(
    @SerialName("id")
    val id: String,
    @SerialName("make")
    val make: String,
    @SerialName("model")
    val model: String,
    @SerialName("year")
    val year: Int,
    @SerialName("type")
    val type: String,
    @SerialName("price_per_day")
    val pricePerDay: Double,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("is_available")
    val isAvailable: Boolean,
)