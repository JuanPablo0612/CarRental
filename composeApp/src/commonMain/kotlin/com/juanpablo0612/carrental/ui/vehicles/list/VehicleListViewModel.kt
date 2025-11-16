package com.juanpablo0612.carrental.ui.vehicles.list

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanpablo0612.carrental.data.vehicles.VehiclesRepository
import com.juanpablo0612.carrental.domain.model.Vehicle
import com.juanpablo0612.carrental.ui.vehicles.list.components.VehicleFilter
import kotlinx.coroutines.launch

class VehicleListViewModel(private val vehiclesRepository: VehiclesRepository) : ViewModel() {
    var uiState by mutableStateOf(VehicleListUiState())
        private set

    private var allVehicles: List<Vehicle> = emptyList()

    val searchFieldState = TextFieldState()

    init {
        loadVehicles()
    }

    fun onSearchQueryChange() {
        filterVehicles()
    }

    fun onFilterChange(filter: VehicleFilter) {
        uiState = uiState.copy(selectedFilter = filter)
        filterVehicles()
    }

    fun retry() {
        loadVehicles()
    }

    private fun filterVehicles() {
        val query = searchFieldState.text.toString().trim().lowercase()
        var filteredVehicles = allVehicles

        if (query.isNotEmpty()) {
            filteredVehicles = filteredVehicles.filter { vehicle ->
                vehicle.make.lowercase().contains(query) ||
                vehicle.model.lowercase().contains(query) ||
                vehicle.type.lowercase().contains(query) ||
                vehicle.year.toString().contains(query)
            }
        }

        filteredVehicles = when (uiState.selectedFilter) {
            VehicleFilter.ALL -> filteredVehicles
            VehicleFilter.AVAILABLE -> filteredVehicles.filter { it.isAvailable }
            VehicleFilter.NOT_AVAILABLE -> filteredVehicles.filter { !it.isAvailable }
        }

        uiState = uiState.copy(vehicles = filteredVehicles)
    }

    private fun loadVehicles() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            vehiclesRepository.getAllVehicles().collect { vehiclesResult ->
                if (vehiclesResult.isSuccess) {
                    val vehicles = vehiclesResult.getOrNull().orEmpty()
                    allVehicles = vehicles
                    uiState = uiState.copy(isLoading = false)
                    filterVehicles()
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = VehicleListError.LoadError(vehiclesResult.exceptionOrNull()?.message)
                    )
                }
            }
        }
    }
}

data class VehicleListUiState(
    val isLoading: Boolean = false,
    val error: VehicleListError? = null,
    val vehicles: List<Vehicle> = emptyList(),
    val selectedFilter: VehicleFilter = VehicleFilter.ALL,
)

sealed class VehicleListError(open val message: String? = null) {
    data class LoadError(override val message: String?) : VehicleListError(message)
}