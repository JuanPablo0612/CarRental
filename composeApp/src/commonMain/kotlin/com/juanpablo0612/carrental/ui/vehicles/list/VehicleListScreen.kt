package com.juanpablo0612.carrental.ui.vehicles.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import carrental.composeapp.generated.resources.Res
import carrental.composeapp.generated.resources.add_vehicle
import carrental.composeapp.generated.resources.vehicles
import com.juanpablo0612.carrental.ui.theme.AppTheme
import com.juanpablo0612.carrental.ui.vehicles.list.components.EmptyVehiclesState
import com.juanpablo0612.carrental.ui.vehicles.list.components.ErrorVehiclesState
import com.juanpablo0612.carrental.ui.vehicles.list.components.VehicleFilter
import com.juanpablo0612.carrental.ui.vehicles.list.components.VehicleFilterChips
import com.juanpablo0612.carrental.ui.vehicles.list.components.VehicleList
import com.juanpablo0612.carrental.ui.vehicles.list.components.VehicleSearchBar
import com.juanpablo0612.carrental.ui.vehicles.list.components.VehicleStats
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VehicleListScreen(
    onNavigateToAddVehicle: () -> Unit,
    onNavigateToVehicleDetail: (vehicleId: String) -> Unit,
    viewModel: VehicleListViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState

    VehicleListScreenContent(
        uiState = uiState,
        onVehicleClick = onNavigateToVehicleDetail,
        onAddVehicle = onNavigateToAddVehicle,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onFilterChange = viewModel::onFilterChange,
        onRetry = viewModel::retry
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VehicleListScreenContent(
    uiState: VehicleListUiState,
    onVehicleClick: (String) -> Unit,
    onAddVehicle: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onFilterChange: (VehicleFilter) -> Unit,
    onRetry: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.vehicles),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddVehicle) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(Res.string.add_vehicle)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (!uiState.isLoading && uiState.error == null) {
                VehicleSearchBar(
                    searchQuery = uiState.searchQuery,
                    onSearchQueryChange = onSearchQueryChange
                )

                VehicleFilterChips(
                    selectedFilter = uiState.selectedFilter,
                    onFilterSelected = onFilterChange
                )

                if (uiState.vehicles.isNotEmpty()) {
                    VehicleStats(
                        totalVehicles = uiState.vehicles.size,
                        availableVehicles = uiState.vehicles.count { it.isAvailable }
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    uiState.error != null -> {
                        ErrorVehiclesState(
                            errorMessage = uiState.error.message,
                            onRetry = onRetry
                        )
                    }

                    uiState.vehicles.isEmpty() && uiState.searchQuery.isEmpty() -> {
                        EmptyVehiclesState(onAddVehicle = onAddVehicle)
                    }

                    uiState.vehicles.isEmpty() && uiState.searchQuery.isNotEmpty() -> {
                        EmptyVehiclesState(
                            onAddVehicle = onAddVehicle,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    else -> {
                        VehicleList(
                            vehicles = uiState.vehicles,
                            onVehicleClick = { vehicle -> onVehicleClick(vehicle.id) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
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
                error = null,
                searchQuery = "",
                selectedFilter = VehicleFilter.ALL
            ),
            onVehicleClick = {},
            onAddVehicle = {},
            onSearchQueryChange = {},
            onFilterChange = {},
            onRetry = {}
        )
    }
}