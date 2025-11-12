package com.juanpablo0612.carrental.data.vehicles

import com.juanpablo0612.carrental.domain.model.Vehicle

interface VehiclesRepository {
    suspend fun getAllVehicles(): Result<List<Vehicle>>
    suspend fun getVehicleById(id: String): Result<Vehicle?>
}

class FakeVehiclesRepository : VehiclesRepository {
    private val vehicles = listOf(
        Vehicle(
            id = "1",
            make = "Toyota",
            model = "Camry",
            year = 2020,
            type = "Sedan",
            pricePerDay = 45.0,
            imageUrl = "https://example.com/images/toyota_camry.jpg",
            isAvailable = true
        ),
        Vehicle(
            id = "2",
            make = "Ford",
            model = "Explorer",
            year = 2019,
            type = "SUV",
            pricePerDay = 60.0,
            imageUrl = "https://example.com/images/ford_explorer.jpg",
            isAvailable = false
        ),
        Vehicle(
            id = "3",
            make = "Honda",
            model = "Civic",
            year = 2021,
            type = "Sedan",
            pricePerDay = 50.0,
            imageUrl = "https://example.com/images/honda_civic.jpg",
            isAvailable = true
        )
    )

    override suspend fun getAllVehicles(): Result<List<Vehicle>> {
        return Result.success(vehicles)
    }

    override suspend fun getVehicleById(id: String): Result<Vehicle?> {
        val vehicle = vehicles.find { it.id == id }
        return Result.success(vehicle)
    }
}