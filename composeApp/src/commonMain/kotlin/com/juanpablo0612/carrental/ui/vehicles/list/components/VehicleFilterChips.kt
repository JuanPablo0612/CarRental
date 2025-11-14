package com.juanpablo0612.carrental.ui.vehicles.list.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import carrental.composeapp.generated.resources.Res
import carrental.composeapp.generated.resources.filter_all
import carrental.composeapp.generated.resources.filter_available
import carrental.composeapp.generated.resources.filter_rented
import org.jetbrains.compose.resources.stringResource

enum class VehicleFilter {
    ALL, AVAILABLE, NOT_AVAILABLE
}

@Composable
fun VehicleFilterChips(
    selectedFilter: VehicleFilter,
    onFilterSelected: (VehicleFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedFilter == VehicleFilter.ALL,
            onClick = { onFilterSelected(VehicleFilter.ALL) },
            label = { Text(stringResource(Res.string.filter_all)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DirectionsCar,
                    contentDescription = null
                )
            }
        )

        FilterChip(
            selected = selectedFilter == VehicleFilter.AVAILABLE,
            onClick = { onFilterSelected(VehicleFilter.AVAILABLE) },
            label = { Text(stringResource(Res.string.filter_available)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null
                )
            }
        )

        FilterChip(
            selected = selectedFilter == VehicleFilter.NOT_AVAILABLE,
            onClick = { onFilterSelected(VehicleFilter.NOT_AVAILABLE) },
            label = { Text(stringResource(Res.string.filter_rented)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
            }
        )
    }
}

