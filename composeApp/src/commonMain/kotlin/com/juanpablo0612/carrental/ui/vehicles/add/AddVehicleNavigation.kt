package com.juanpablo0612.carrental.ui.vehicles.add

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object AddVehicleDestination

fun NavGraphBuilder.addVehicleDestination(
    onNavigateBack: () -> Unit
) {
    composable<AddVehicleDestination> {
        AddVehicleScreen(
            onNavigateBack = onNavigateBack
        )
    }
}

