package com.juanpablo0612.carrental.ui.vehicles.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.juanpablo0612.carrental.ui.theme.AppTheme
import com.juanpablo0612.carrental.ui.vehicles.list.components.VehicleList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun VehicleListScreen(
    onNavigateToAddVehicle: () -> Unit,
    onNavigateToVehicleDetail: (vehicleId: String) -> Unit,
    viewModel: VehicleListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val uiState = viewModel.uiState

    VehicleListScreenContent(
        uiState = uiState,
        onVehicleClick = onNavigateToVehicleDetail,
        onAddVehicle = onNavigateToAddVehicle,
    )
}

@Composable
private fun VehicleListScreenContent(
    uiState: VehicleListUiState,
    onVehicleClick: (String) -> Unit,
    onAddVehicle: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddVehicle) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.vehicles.isNotEmpty() -> {
                    VehicleList(
                        vehicles = uiState.vehicles,
                        onVehicleClick = { vehicle -> onVehicleClick(vehicle.id) },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleListScreenContentPreview() {
    AppTheme {
        VehicleListScreenContent(
            uiState = VehicleListUiState(
                isLoading = false,
                vehicles = emptyList(),
                error = null
            ),
            onVehicleClick = {},
            onAddVehicle = {}
        )
    }
}