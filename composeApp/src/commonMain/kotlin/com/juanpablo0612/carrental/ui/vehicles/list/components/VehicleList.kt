package com.juanpablo0612.carrental.ui.vehicles.list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juanpablo0612.carrental.domain.model.Vehicle

@Composable
fun VehicleList(
    vehicles: List<Vehicle>,
    onVehicleClick: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(vehicles, key = { it.id }) { vehicle ->
            VehicleListItem(vehicle = vehicle, onClick = { onVehicleClick(vehicle) })
        }
    }
}