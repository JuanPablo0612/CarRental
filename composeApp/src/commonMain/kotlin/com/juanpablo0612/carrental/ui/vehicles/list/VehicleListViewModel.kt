package com.juanpablo0612.carrental.ui.vehicles.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanpablo0612.carrental.data.vehicles.VehiclesRepository
import com.juanpablo0612.carrental.domain.model.Vehicle
import kotlinx.coroutines.launch

class VehicleListViewModel(private val vehiclesRepository: VehiclesRepository) : ViewModel() {
    var uiState by mutableStateOf(VehicleListUiState())
        private set

    init {
        loadVehicles()
    }

    private fun loadVehicles() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            val vehiclesResult = vehiclesRepository.getAllVehicles()

            if (vehiclesResult.isSuccess) {
                val vehicles = vehiclesResult.getOrNull().orEmpty()
                uiState = uiState.copy(isLoading = false, vehicles = vehicles)
            } else {
                uiState = uiState.copy(
                    isLoading = false,
                    error = VehicleListError.LoadError(vehiclesResult.exceptionOrNull()?.message)
                )
            }
        }
    }
}

data class VehicleListUiState(
    val isLoading: Boolean = false,
    val error: VehicleListError? = null,
    val vehicles: List<Vehicle> = emptyList(),
)

sealed class VehicleListError(open val message: String? = null) {
    data class LoadError(override val message: String?) : VehicleListError(message)
}