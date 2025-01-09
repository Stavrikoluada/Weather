package com.example.weather

import com.example.weather.data.WeatherModel
import org.json.JSONArray
import org.json.JSONObject

class HoursWeather {
    fun getWeatherByHours(hours: String): List<WeatherModel> {
        if (hours.isEmpty()) return listOf()
        val list = ArrayList<WeatherModel>()
        val hoursArray = JSONArray(hours)

        for (i in 0 until hoursArray.length()) {
            val item = hoursArray[i] as JSONObject

            list.add(
                WeatherModel(
                    city = "",
                    time = item.getString("time"),
                    currentTemp = item.getString("temp_c").toFloat().toInt().toString() + "°C",
                    condition = item.getJSONObject("condition").getString("text"),
                    icon = item.getJSONObject("condition").getString("icon"),
                    maxTemp = "",
                    minTemp = "",
                    hours = "",
                )
            )
        }
        return list
    }
}