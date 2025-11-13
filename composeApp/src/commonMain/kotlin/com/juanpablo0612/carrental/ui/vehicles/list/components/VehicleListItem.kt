package com.juanpablo0612.carrental.ui.vehicles.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.juanpablo0612.carrental.domain.model.Vehicle
import com.juanpablo0612.carrental.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun VehicleListItem(vehicle: Vehicle, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(modifier = modifier, onClick = onClick) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                AsyncImage(
                    model = vehicle.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(40.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = vehicle.model)
                    Text(text = vehicle.make)
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleSummaryCardPreview() {
    AppTheme {
        VehicleListItem(
            vehicle = Vehicle(
                id = "1",
                make = "Toyota",
                model = "Camry",
                year = 2020,
                type = "Sedan",
                pricePerDay = 45.99,
                imageUrl = "",
                isAvailable = true,
            ),
            onClick = {}
        )
    }
}