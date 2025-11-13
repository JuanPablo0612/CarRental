package com.juanpablo0612.carrental.ui.vehicles.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class VehicleDetailDestination(
    val id: String
)

fun NavGraphBuilder.vehicleDetailDestination(onNavigateBack: () -> Unit) {
    composable<VehicleDetailDestination> {
        VehicleDetailScreen(onNavigateBack = onNavigateBack)
    }
}

fun NavController.navigateToVehicleDetail(vehicleId: String) {
    navigate(VehicleDetailDestination(vehicleId))
}
