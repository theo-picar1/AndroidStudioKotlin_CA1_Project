package com.example.mobile_intergration_ca1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile_intergration_ca1.ui.theme.Mobile_Intergration_CA1Theme

class MainActivity : ComponentActivity() {
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

@Preview(showBackground = true)
@Composable
fun FuelCostCalculator(modifier: Modifier = Modifier) {
    var distance by remember { mutableStateOf("") }
    var efficiency by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var totalCost by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = distance,
            onValueChange = { distance = it },
            label = { Text("Distance (km)") }
        )

        OutlinedTextField(
            value = efficiency,
            onValueChange = { efficiency = it },
            label = { Text("Fuel Efficiency (L/100km)") }
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Fuel Price (per L)") }
        )

        Button(onClick = {
            // Default to 0 if did not type anything inside input
            val d = distance.toDoubleOrNull() ?: 0.0
            val e = efficiency.toDoubleOrNull() ?: 0.0
            val p = price.toDoubleOrNull() ?: 0.0
            val cost = if (e != 0.0) (d / 100) * e * p else 0.0
            totalCost = "Total Cost: €${"%.2f".format(cost)}"
        }) {
            Text("Calculate")
        }

        Text(text = totalCost)
    }
}
