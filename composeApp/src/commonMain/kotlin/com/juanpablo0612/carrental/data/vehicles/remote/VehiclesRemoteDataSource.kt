package com.juanpablo0612.carrental.data.vehicles.remote

import com.juanpablo0612.carrental.data.vehicles.model.VehicleModel
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val VEHICLES_COLLECTION = "vehicles"

class VehiclesRemoteDataSource(firestore: FirebaseFirestore) {
    private val vehiclesCollection = firestore.collection(VEHICLES_COLLECTION)

    fun getAllVehicles(): Flow<List<VehicleModel>> =
        vehiclesCollection
            .snapshots
            .map { snapshot -> snapshot.documents.mapNotNull { doc -> doc.data() } }

    fun getVehicleById(id: String): Flow<VehicleModel> {
        return vehiclesCollection
            .document(id)
            .snapshots
            .map { doc -> doc.data() }
    }

    suspend fun addVehicle(vehicle: VehicleModel) {
        vehiclesCollection.document(vehicle.id).set(vehicle, merge = false)
    }

    suspend fun updateVehicle(vehicle: VehicleModel, merge: Boolean = true) {
        vehiclesCollection.document(vehicle.id).set(vehicle, merge = merge)
    }

    suspend fun deleteVehicle(id: String) {
        vehiclesCollection.document(id).delete()
    }
}