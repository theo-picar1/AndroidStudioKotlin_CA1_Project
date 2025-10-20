package com.example.mobile_intergration_ca1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_intergration_ca1.ui.theme.Mobile_Intergration_CA1Theme
import Affirmation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mobile_intergration_ca1.data.Datasource
import android.util.Log

class App2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobile_Intergration_CA1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScrollableApp()
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun AffirmationCardPreview() {
//    CountryCard(Affirmation(R.string.affirmation1, R.drawable.image1))
//}

@Composable
fun ScrollableApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(20.dp)
    ) {
        ScrollableList(
            affirmationList = Datasource().loadAffirmations(),
        )
    }
}

@Composable
fun ScrollableList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    var radioOption by remember { mutableStateOf("All") }
    var list by remember { mutableStateOf(affirmationList) }

    // Higher order function. Filter between Europe, Asian or All
    if(radioOption == "Europe") {
        list = affirmationList.filter { a ->
            LocalContext.current.getString(a.continentResourceId) == "Europe"
        }
    }
    else if(radioOption == "Asia") {
        list = affirmationList.filter { a ->
            LocalContext.current.getString(a.continentResourceId) == "Asia"
        }
    }
    else list = affirmationList

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                RadioButton(
                    selected = radioOption == "All",
                    onClick = {
                        radioOption = "All"
                        Log.d("ScrollableList", "Choosing all countries in the continent $radioOption")
                    }
                )
                Text("All")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                RadioButton(
                    selected = radioOption == "Europe",
                    onClick = {
                        radioOption = "Europe"
                        Log.d("ScrollableList", "Choosing all countries in the continent $radioOption")
                    }
                )
                Text("Europe")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                RadioButton(
                    selected = radioOption == "Asia",
                    onClick = {
                        radioOption = "Asia"
                        Log.d("ScrollableList", "Choosing all countries in the continent $radioOption")
                    }
                )
                Text("Asia")
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(list) { affirmation ->
                CountryCard(
                    affirmation = affirmation
                )
            }
        }
    }
}

@Composable
fun CountryCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    val title = LocalContext.current.getString(affirmation.titleResourceId)

    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(194.dp),
            )

            Box(
                modifier = modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = LocalContext.current.getString(affirmation.titleResourceId),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                    if (isExpanded) {
                        Text(
                            text = LocalContext.current.getString(affirmation.stringResourceId),
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }

                    Button(
                        onClick = {
                            isExpanded = !isExpanded
                            logCardMessage(isExpanded, title)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6200EE),
                            contentColor = Color.White
                        ),
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(text = if (isExpanded) "Show less" else "Read more")
                    }
                }
            }
        }
    }
}

fun logCardMessage(isExpanded: Boolean, title: String) {
    if(isExpanded) {
        Log.d("CountryCard", "Expanded the '$title' card")
    }
    else {
        Log.d("CountryCard", "Closed the '$title' card")
    }
}