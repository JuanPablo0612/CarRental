package com.juanpablo0612.carrental.ui.vehicles.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juanpablo0612.carrental.domain.model.Vehicle

@Composable
fun VehicleList(
    vehicles: List<Vehicle>,
    onVehicleClick: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(vehicles, key = { it.id }) { vehicle ->
            VehicleListItem(
                vehicle = vehicle,
                onClick = { onVehicleClick(vehicle) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}