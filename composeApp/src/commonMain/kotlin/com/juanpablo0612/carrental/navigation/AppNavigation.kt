package com.juanpablo0612.carrental.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.juanpablo0612.carrental.ui.vehicles.detail.navigateToVehicleDetail
import com.juanpablo0612.carrental.ui.vehicles.list.VehicleListDestination
import com.juanpablo0612.carrental.ui.vehicles.list.vehicleListDestination

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = VehicleListDestination) {
        vehicleListDestination(
            onNavigateToVehicleDetail = { vehicleId ->
                navController.navigateToVehicleDetail(vehicleId)
            },
            onNavigateToAddVehicle = {}
        )
    }
}