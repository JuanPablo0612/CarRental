package com.juanpablo0612.carrental.ui.vehicles.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object VehicleListDestination

fun NavGraphBuilder.vehicleListDestination(
    onNavigateToAddVehicle: () -> Unit,
    onNavigateToVehicleDetail: (vehicleId: String) -> Unit
) {
    composable<VehicleListDestination> {
        VehicleListScreen(
            onNavigateToAddVehicle = onNavigateToAddVehicle,
            onNavigateToVehicleDetail = onNavigateToVehicleDetail
        )
    }
}