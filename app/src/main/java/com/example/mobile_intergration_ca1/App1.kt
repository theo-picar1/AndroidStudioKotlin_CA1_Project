package com.example.mobile_intergration_ca1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_intergration_ca1.ui.theme.Mobile_Intergration_CA1Theme

class App1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobile_Intergration_CA1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FuelCostCalculator(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun FuelCostCalculator(modifier: Modifier = Modifier) {
    var inputDistance by remember { mutableStateOf("") }
    var inputEfficiency by remember { mutableStateOf("") }
    var inputPrice by remember { mutableStateOf("") }
    var totalCost by remember { mutableStateOf("") }
    var isRoundTrip by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(24.dp)
            .safeDrawingPadding()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.app1_icon),
                    contentDescription = stringResource(R.string.app1_icon_desc),
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Fuel Cost Calculator",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                OutlinedTextField(
                    value = inputDistance,
                    onValueChange = { inputDistance = it },
                    label = { Text("Distance (km)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = inputEfficiency,
                    onValueChange = { inputEfficiency = it },
                    label = { Text("Fuel Efficiency (L/100km)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = inputPrice,
                    onValueChange = { inputPrice = it },
                    label = { Text("Fuel Price (per L)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Has left padding for some reason
                    Checkbox(
                        checked = isRoundTrip,
                        onCheckedChange = { isRoundTrip = it },
                        modifier = Modifier.padding(0.dp)
                    )
                    Text("Round Trip")
                }
            }
        }

        Button(
            onClick = {
                val distance = inputDistance.toDoubleOrNull() ?: 0.0
                val efficiency = inputEfficiency.toDoubleOrNull() ?: 0.0
                val price = inputPrice.toDoubleOrNull() ?: 0.0
                val cost = calculateFuelCost(distance, efficiency, price, isRoundTrip)
                totalCost = "Total Cost: €${"%.2f".format(cost)}"
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Calculate",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = totalCost,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                if (totalCost.isNotEmpty()) {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Text("✓", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}

// Separated for UnitTesting
fun calculateFuelCost(
    distance: Double,
    efficiency: Double,
    price: Double,
    isRoundTrip: Boolean
): Double {
    val totalDistance = if (isRoundTrip) distance * 2 else distance
    return if (efficiency != 0.0) (totalDistance / 100) * efficiency * price else 0.0
}