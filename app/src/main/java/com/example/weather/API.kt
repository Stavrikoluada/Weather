package com.example.weather

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.data.WeatherModel

const val API_KEY = "88e829fb3fba4d3faf5124809242911"
class API {
    val daysWeather = DaysWeather()

    fun getData(city: String, context: Context, daysList: MutableState<List<WeatherModel>>, currentDay: MutableState<WeatherModel>) {
        val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY&q=$city&days=3&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                response ->
                val list = daysWeather.getWeatherByDays(response)
                currentDay.value = list[0]
                daysList.value = list
            },
            {
                error ->
                Log.d("MyLog", "Volly Error $error")
            }
        )
        queue.add(stringRequest)
    }
}