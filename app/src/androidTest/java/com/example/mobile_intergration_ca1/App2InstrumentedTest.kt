package com.example.mobile_intergration_ca1

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class App2InstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<App2>()

    // Tests to see if read more / less button works as intended
    @Test
    fun Test1() {
        composeTestRule.waitForIdle()
        composeTestRule.onAllNodesWithText("Read more")[0].performClick()

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Show less").assertIsDisplayed()
    }
}