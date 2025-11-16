package com.juanpablo0612.carrental.ui.vehicles.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import carrental.composeapp.generated.resources.Res
import carrental.composeapp.generated.resources.add_vehicle_title
import carrental.composeapp.generated.resources.back
import carrental.composeapp.generated.resources.image_url_placeholder
import carrental.composeapp.generated.resources.make_placeholder
import carrental.composeapp.generated.resources.model_placeholder
import carrental.composeapp.generated.resources.price_placeholder
import carrental.composeapp.generated.resources.save
import carrental.composeapp.generated.resources.type_placeholder
import carrental.composeapp.generated.resources.vehicle_added_success
import carrental.composeapp.generated.resources.vehicle_image_url
import carrental.composeapp.generated.resources.vehicle_make
import carrental.composeapp.generated.resources.vehicle_model
import carrental.composeapp.generated.resources.vehicle_price_per_day
import carrental.composeapp.generated.resources.vehicle_type
import carrental.composeapp.generated.resources.vehicle_year
import carrental.composeapp.generated.resources.year_placeholder
import com.juanpablo0612.carrental.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddVehicleScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddVehicleViewModel = koinViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val successMessage = stringResource(Res.string.vehicle_added_success)

    LaunchedEffect(viewModel.uiState.isAdded) {
        if (viewModel.uiState.isAdded) {
            snackbarHostState.showSnackbar(successMessage)
            onNavigateBack()
        }
    }

    AddVehicleScreenContent(
        uiState = viewModel.uiState,
        makeState = viewModel.mark,
        modelState = viewModel.model,
        yearState = viewModel.year,
        typeState = viewModel.type,
        pricePerDayState = viewModel.pricePerDay,
        imageUrlState = viewModel.imageUrl,
        validateMake = viewModel::validateMake,
        validateModel = viewModel::validateModel,
        validateYear = viewModel::validateYear,
        validateType = viewModel::validateType,
        validatePrice = viewModel::validatePrice,
        validateImageUrl = viewModel::validateImageUrl,
        onNavigateBack = onNavigateBack,
        onSaveClick = viewModel::onSaveClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddVehicleScreenContent(
    uiState: AddVehicleUiState,
    makeState: TextFieldState,
    modelState: TextFieldState,
    yearState: TextFieldState,
    typeState: TextFieldState,
    pricePerDayState: TextFieldState,
    imageUrlState: TextFieldState,
    validateMake: () -> Unit,
    validateModel: () -> Unit,
    validateYear: () -> Unit,
    validateType: () -> Unit,
    validatePrice: () -> Unit,
    validateImageUrl: () -> Unit,
    onNavigateBack: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.add_vehicle_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    state = makeState,
                    label = { Text(stringResource(Res.string.vehicle_make)) },
                    placeholder = { Text(stringResource(Res.string.make_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isAdding,
                    isError = uiState.makeError != null,
                    supportingText = uiState.makeError?.let { { Text(it) } },
                    onKeyboardAction = { performDefaultAction ->
                        validateMake()
                        performDefaultAction()
                    }
                )

                OutlinedTextField(
                    state = modelState,
                    label = { Text(stringResource(Res.string.vehicle_model)) },
                    placeholder = { Text(stringResource(Res.string.model_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isAdding,
                    isError = uiState.modelError != null,
                    supportingText = uiState.modelError?.let { { Text(it) } },
                    onKeyboardAction = { performDefaultAction ->
                        validateModel()
                        performDefaultAction()
                    }
                )

                OutlinedTextField(
                    state = yearState,
                    label = { Text(stringResource(Res.string.vehicle_year)) },
                    placeholder = { Text(stringResource(Res.string.year_placeholder)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isAdding,
                    isError = uiState.yearError != null,
                    supportingText = uiState.yearError?.let { { Text(it) } },
                    onKeyboardAction = { performDefaultAction ->
                        validateYear()
                        performDefaultAction()
                    }
                )

                OutlinedTextField(
                    state = typeState,
                    label = { Text(stringResource(Res.string.vehicle_type)) },
                    placeholder = { Text(stringResource(Res.string.type_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isAdding,
                    isError = uiState.typeError != null,
                    supportingText = uiState.typeError?.let { { Text(it) } },
                    onKeyboardAction = { performDefaultAction ->
                        validateType()
                        performDefaultAction()
                    }
                )

                OutlinedTextField(
                    state = pricePerDayState,
                    label = { Text(stringResource(Res.string.vehicle_price_per_day)) },
                    placeholder = { Text(stringResource(Res.string.price_placeholder)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isAdding,
                    isError = uiState.priceError != null,
                    supportingText = uiState.priceError?.let { { Text(it) } },
                    onKeyboardAction = { performDefaultAction ->
                        validatePrice()
                        performDefaultAction()
                    }
                )

                OutlinedTextField(
                    state = imageUrlState,
                    label = { Text(stringResource(Res.string.vehicle_image_url)) },
                    placeholder = { Text(stringResource(Res.string.image_url_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isAdding,
                    isError = uiState.imageUrlError != null,
                    supportingText = uiState.imageUrlError?.let { { Text(it) } },
                    onKeyboardAction = { performDefaultAction ->
                        validateImageUrl()
                        performDefaultAction()
                    }
                )

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    enabled = !uiState.isAdding,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (uiState.isAdding) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(Res.string.save),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddVehicleScreenPreview() {
    AppTheme {
        AddVehicleScreenContent(
            uiState = AddVehicleUiState(),
            makeState = TextFieldState(),
            modelState = TextFieldState(),
            yearState = TextFieldState(),
            typeState = TextFieldState(),
            pricePerDayState = TextFieldState(),
            imageUrlState = TextFieldState(),
            validateMake = {},
            validateModel = {},
            validateYear = {},
            validateType = {},
            validatePrice = {},
            validateImageUrl = {},
            onNavigateBack = {},
            onSaveClick = {}
        )
    }
}