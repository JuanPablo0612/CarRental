package com.juanpablo0612.carrental.data.vehicles

import com.juanpablo0612.carrental.domain.model.Vehicle

interface VehiclesRepository {
    suspend fun getAllVehicles(): Result<List<Vehicle>>
    suspend fun getVehicleById(id: String): Result<Vehicle?>
    suspend fun addVehicle(vehicle: Vehicle): Result<Unit>
}

class FakeVehiclesRepository : VehiclesRepository {
    private val vehicles = mutableListOf(
        Vehicle(
            id = "1",
            make = "Toyota",
            model = "Camry",
            year = 2020,
            type = "Sedan",
            pricePerDay = 45.0,
            imageUrl = "https://media.ed.edmunds-media.com/toyota/camry/2020/oem/2020_toyota_camry_sedan_se_fq_oem_2_1600.jpg",
            isAvailable = true
        ),
        Vehicle(
            id = "2",
            make = "Ford",
            model = "Explorer",
            year = 2019,
            type = "SUV",
            pricePerDay = 60.0,
            imageUrl = "https://images.hgmsites.net/lrg/2019-ford-explorer-xlt-4wd-angular-front-exterior-view_100675940_l.jpg",
            isAvailable = false
        ),
        Vehicle(
            id = "3",
            make = "Honda",
            model = "Civic",
            year = 2021,
            type = "Sedan",
            pricePerDay = 50.0,
            imageUrl = "https://autotest.com.ar/wp-content/uploads/2020/10/honda-civic-2021.jpg",
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

    override suspend fun addVehicle(vehicle: Vehicle): Result<Unit> {
        vehicles.add(vehicle)
        return Result.success(Unit)
    }
}