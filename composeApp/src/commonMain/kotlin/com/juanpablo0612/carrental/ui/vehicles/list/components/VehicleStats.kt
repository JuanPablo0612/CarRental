package com.juanpablo0612.carrental.ui.vehicles.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import carrental.composeapp.generated.resources.Res
import carrental.composeapp.generated.resources.stats_available
import carrental.composeapp.generated.resources.stats_rented
import carrental.composeapp.generated.resources.stats_total
import org.jetbrains.compose.resources.stringResource

@Composable
fun VehicleStats(
    totalVehicles: Int,
    availableVehicles: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(
            label = stringResource(Res.string.stats_total),
            value = totalVehicles.toString(),
            modifier = Modifier.weight(1f)
        )

        StatItem(
            label = stringResource(Res.string.stats_available),
            value = availableVehicles.toString(),
            modifier = Modifier.weight(1f)
        )

        StatItem(
            label = stringResource(Res.string.stats_rented),
            value = (totalVehicles - availableVehicles).toString(),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StatItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = " $label",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
        )
    }
}

