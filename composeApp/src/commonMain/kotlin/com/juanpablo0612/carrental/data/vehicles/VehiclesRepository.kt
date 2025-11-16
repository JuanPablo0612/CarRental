package com.juanpablo0612.carrental.data.vehicles

import com.juanpablo0612.carrental.data.vehicles.model.toDomain
import com.juanpablo0612.carrental.data.vehicles.remote.VehiclesRemoteDataSource
import com.juanpablo0612.carrental.domain.model.Vehicle
import com.juanpablo0612.carrental.domain.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface VehiclesRepository {
    fun getAllVehicles(): Flow<Result<List<Vehicle>>>
    fun getVehicleById(id: String): Flow<Result<Vehicle?>>
    suspend fun addVehicle(vehicle: Vehicle): Result<Unit>
}

class VehiclesRepositoryImpl(private val remoteDataSource: VehiclesRemoteDataSource) :
    VehiclesRepository {
    override fun getAllVehicles(): Flow<Result<List<Vehicle>>> {
        return remoteDataSource
            .getAllVehicles().map {
                Result.success(it.map { model -> model.toDomain() })
            }
            .catch { emit(Result.failure(it)) }
    }

    override fun getVehicleById(id: String): Flow<Result<Vehicle?>> {
        return remoteDataSource
            .getVehicleById(id).map {
                Result.success(it.toDomain())
            }
            .catch { emit(Result.failure(it)) }
    }

    override suspend fun addVehicle(vehicle: Vehicle): Result<Unit> {
        return try {
            remoteDataSource.addVehicle(vehicle.toModel())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
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
            imageUrl = "https://img.asmedia.epimg.net/resizer/v2/RDOPBNRDS5KLJGZ74ENN4RYYLY.jpg?auth=249f81b9e335f97730b76b1edbe9ac8878283a43f1c96957b912cdc91fc67d2d&width=1472&height=1104&smart=true",
            isAvailable = true
        ),
        Vehicle(
            id = "2",
            make = "Honda",
            model = "Civic",
            year = 2019,
            type = "Sedan",
            pricePerDay = 40.0,
            imageUrl = "https://di-uploads-pod16.dealerinspire.com/pattypeckhonda/uploads/2018/10/2019-Civic-sedan-lx-trim-600x335.png",
            isAvailable = false
        ),
        Vehicle(
            id = "3",
            make = "Ford",
            model = "Explorer",
            year = 2021,
            type = "SUV",
            pricePerDay = 60.0,
            imageUrl = "https://di-uploads-pod39.dealerinspire.com/kingsford/uploads/2021/02/2021-Ford-Explorer-Overview-Left.jpg",
            isAvailable = true
        )
    )

    override fun getAllVehicles(): Flow<Result<List<Vehicle>>> {
        return flow {
            emit(Result.success(vehicles.toList()))
        }
    }

    override fun getVehicleById(id: String): Flow<Result<Vehicle?>> {
        return flow {
            val vehicle = vehicles.find { it.id == id }
            emit(Result.success(vehicle))
        }
    }

    override suspend fun addVehicle(vehicle: Vehicle): Result<Unit> {
        vehicles.add(vehicle)
        return Result.success(Unit)
    }
}