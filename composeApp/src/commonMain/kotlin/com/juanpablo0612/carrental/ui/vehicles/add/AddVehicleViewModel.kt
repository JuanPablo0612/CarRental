package com.juanpablo0612.carrental.ui.vehicles.add

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanpablo0612.carrental.data.vehicles.VehiclesRepository
import com.juanpablo0612.carrental.domain.model.Vehicle
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AddVehicleViewModel(private val vehiclesRepository: VehiclesRepository) : ViewModel() {
    var uiState by mutableStateOf(AddVehicleUiState())
        private set

    val mark = TextFieldState()
    val model = TextFieldState()
    val year = TextFieldState()
    val type = TextFieldState()
    val pricePerDay = TextFieldState()
    val imageUrl = TextFieldState()

    private fun validate(
        condition: Boolean,
        errorKey: String,
        updateState: (String?) -> Unit
    ) {
        if (condition) {
            updateState(errorKey)
        } else {
            updateState(null)
        }
    }

    fun validateMake() {
        validate(mark.text.toString().trim().isEmpty(), "field_required") {
            uiState = uiState.copy(makeError = it)
        }
    }

    fun validateModel() {
        validate(model.text.toString().trim().isEmpty(), "field_required") {
            uiState = uiState.copy(modelError = it)
        }
    }

    fun validateYear() {
        val yearValue = year.text.toString().trim().toIntOrNull()
        validate(yearValue == null || yearValue < 1900 || yearValue > 2100, "invalid_year") {
            uiState = uiState.copy(yearError = it)
        }
    }

    fun validateType() {
        validate(type.text.toString().trim().isEmpty(), "field_required") { uiState = uiState.copy(typeError = it) }
    }

    fun validatePrice() {
        val priceValue = pricePerDay.text.toString().trim().toDoubleOrNull()
        validate(priceValue == null || priceValue <= 0, "invalid_price") {
            uiState = uiState.copy(priceError = it)
        }
    }

    fun validateImageUrl() {
        validate(imageUrl.text.toString().trim().isEmpty(), "field_required") {
            uiState = uiState.copy(imageUrlError = it)
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun onSaveClick() {
        if (!validateForm()) {
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isAdding = true, error = null)

            try {
                val vehicle = Vehicle(
                    id = Uuid.random().toString(),
                    make = mark.text.toString().trim(),
                    model = model.text.toString().trim(),
                    year = year.text.toString().trim().toInt(),
                    type = type.text.toString().trim(),
                    pricePerDay = pricePerDay.text.toString().trim().toDouble(),
                    imageUrl = imageUrl.text.toString().trim(),
                    isAvailable = true
                )

                val result = vehiclesRepository.addVehicle(vehicle)

                if (result.isSuccess) {
                    uiState = uiState.copy(isAdding = false, isAdded = true)
                } else {
                    uiState = uiState.copy(
                        isAdding = false,
                        error = AddVehicleError.AddError(result.exceptionOrNull()?.message)
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isAdding = false,
                    error = AddVehicleError.AddError(e.message)
                )
            }
        }
    }

    private fun validateForm(): Boolean {
        validateMake()
        validateModel()
        validateYear()
        validateType()
        validatePrice()
        validateImageUrl()

        return uiState.makeError == null && uiState.modelError == null && uiState.yearError == null &&
                uiState.typeError == null && uiState.priceError == null && uiState.imageUrlError == null
    }
}

data class AddVehicleUiState(
    val isAdding: Boolean = false,
    val error: AddVehicleError? = null,
    val isAdded: Boolean = false,
    val makeError: String? = null,
    val modelError: String? = null,
    val yearError: String? = null,
    val typeError: String? = null,
    val priceError: String? = null,
    val imageUrlError: String? = null,
)

sealed class AddVehicleError(open val message: String?) {
    data class AddError(override val message: String?): AddVehicleError(message)
}