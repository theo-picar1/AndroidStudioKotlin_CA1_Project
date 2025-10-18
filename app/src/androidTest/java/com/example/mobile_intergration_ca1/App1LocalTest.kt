package com.example.mobile_intergration_ca1

import org.junit.Assert.assertEquals
import org.junit.Test

class App1LocalTest {

    // Tests to see if fuel cost calculations are correct (w/ round trip)
    @Test
    fun Test1() {
        val result = calculateFuelCost(
            distance = 100.0,
            efficiency = 5.0,
            price = 2.0,
            isRoundTrip = true
        )
        // Allow 0.001 leeway
        assertEquals(20.0, result, 0.001)
    }
}