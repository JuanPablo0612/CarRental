package com.juanpablo0612.carrental.ui.vehicles.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.juanpablo0612.carrental.data.vehicles.VehiclesRepository
import com.juanpablo0612.carrental.domain.model.Vehicle
import kotlinx.coroutines.launch

class VehicleDetailViewModel(
    private val vehiclesRepository: VehiclesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(VehicleDetailUiState())
        private set

    private val vehicleDetail = savedStateHandle.toRoute(VehicleDetailDestination::class)

    init {
        loadVehicle(vehicleDetail.id)
    }

    private fun loadVehicle(id: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            val vehicleResult = vehiclesRepository.getVehicleById(id)

            if (vehicleResult.isSuccess) {
                val vehicle = vehicleResult.getOrNull()
                uiState = uiState.copy(isLoading = false, vehicle = vehicle)
            } else {
                uiState = uiState.copy(
                    isLoading = false,
                    error = VehicleDetailError.LoadError(vehicleResult.exceptionOrNull()?.message)
                )
            }
        }
    }
}

data class VehicleDetailUiState(
    val isLoading: Boolean = false,
    val error: VehicleDetailError? = null,
    val vehicle: Vehicle? = null,
)

sealed class VehicleDetailError(open val message: String? = null) {
    data class LoadError(override val message: String?) : VehicleDetailError(message)
}