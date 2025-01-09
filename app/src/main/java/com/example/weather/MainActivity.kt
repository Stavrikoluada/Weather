package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weather.ui.theme.WeatherTheme
import com.example.weather.data.WeatherModel
import com.example.weather.screens.DialogSearch
import com.example.weather.screens.MainCard
import com.example.weather.screens.TabLayout

class MainActivity : ComponentActivity() {
    val api = API()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val daysList = remember {
                mutableStateOf(listOf<WeatherModel>())
            }
            val dialogState = remember {
                mutableStateOf(false)
            }

            val currentDay = remember {
                mutableStateOf(WeatherModel("", "","0.0","","","0.0","0.0",""))
            }

            if (dialogState.value) DialogSearch(dialogState, onSubmit = {
                api.getData(it, this, daysList, currentDay)
            })

            api.getData("London", this, daysList, currentDay)
            Image(painter = painterResource(R.drawable.fon_main),
                contentDescription = "im_1",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.7f),
                contentScale = ContentScale.FillBounds)

            WeatherTheme {
                Column {
                    MainCard(currentDay, onClickSync = {
                        api.getData("London", this@MainActivity, daysList, currentDay)
                    }, onClickSearch = {
                        dialogState.value = true
                    })
                    TabLayout(daysList, currentDay)
                }
            }
        }
    }
}













