package com.juanpablo0612.carrental.ui.vehicles.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import carrental.composeapp.generated.resources.Res
import carrental.composeapp.generated.resources.not_available
import carrental.composeapp.generated.resources.rent_now
import com.juanpablo0612.carrental.domain.model.Vehicle
import com.juanpablo0612.carrental.ui.components.CenteredCircularProgress
import com.juanpablo0612.carrental.ui.components.PrimaryActionButton
import com.juanpablo0612.carrental.ui.theme.AppTheme
import com.juanpablo0612.carrental.ui.vehicles.detail.components.AvailabilityBadge
import com.juanpablo0612.carrental.ui.vehicles.detail.components.PriceSection
import com.juanpablo0612.carrental.ui.vehicles.detail.components.VehicleDetailTopBar
import com.juanpablo0612.carrental.ui.vehicles.detail.components.VehicleHeader
import com.juanpablo0612.carrental.ui.vehicles.detail.components.VehicleImage
import com.juanpablo0612.carrental.ui.vehicles.detail.components.VehicleInfoCard
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VehicleDetailScreen(
    onNavigateBack: () -> Unit,
    viewModel: VehicleDetailViewModel = koinViewModel()
) {
    VehicleDetailScreenContent(
        uiState = viewModel.uiState,
        onNavigateBack = onNavigateBack,
        onRentClick = {}
    )
}

@Composable
private fun VehicleDetailScreenContent(
    uiState: VehicleDetailUiState,
    onNavigateBack: () -> Unit,
    onRentClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            VehicleDetailTopBar(onNavigateBack = onNavigateBack)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    CenteredCircularProgress()
                }

                uiState.vehicle != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        VehicleImage(imageUrl = uiState.vehicle.imageUrl)

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            VehicleHeader(
                                make = uiState.vehicle.make,
                                model = uiState.vehicle.model,
                                year = uiState.vehicle.year
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            AvailabilityBadge(isAvailable = uiState.vehicle.isAvailable)

                            Spacer(modifier = Modifier.height(24.dp))

                            VehicleInfoCard(vehicle = uiState.vehicle)

                            Spacer(modifier = Modifier.height(24.dp))

                            PriceSection(pricePerDay = uiState.vehicle.pricePerDay)

                            Spacer(modifier = Modifier.height(24.dp))

                            PrimaryActionButton(
                                text = stringResource(if (uiState.vehicle.isAvailable) Res.string.rent_now else Res.string.not_available),
                                isLoading = false,
                                enabled = uiState.vehicle.isAvailable,
                                onClick = onRentClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                heightDp = 56
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun VehicleDetailScreenPreview() {
    AppTheme {
        VehicleDetailScreenContent(
            uiState = VehicleDetailUiState(
                isLoading = false,
                vehicle = Vehicle(
                    id = "1",
                    make = "Toyota",
                    model = "Corolla",
                    year = 2020,
                    type = "Sedan",
                    isAvailable = true,
                    pricePerDay = 45.0,
                    imageUrl = "https://example.com/vehicle.jpg"
                )
            ),
            onNavigateBack = {},
            onRentClick = {}
        )
    }
}