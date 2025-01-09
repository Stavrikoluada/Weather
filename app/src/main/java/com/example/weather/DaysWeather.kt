package com.example.weather

import com.example.weather.data.WeatherModel
import org.json.JSONObject

class DaysWeather {

    fun getWeatherByDays(response: String): List<WeatherModel> {
        if (response.isEmpty()) return listOf()
        val list = ArrayList<WeatherModel>()
        val mainObject = JSONObject(response)
        val city = mainObject.getJSONObject("location").getString("name")
        val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

        for (i in 0 until days.length()) {
            val item = days[i] as JSONObject

            list.add(
                WeatherModel(
                    city = city,
                    time = item.getString("date"),
                    currentTemp = "",
                    condition = item.getJSONObject("day").getJSONObject("condition").getString("text"),
                    icon = item.getJSONObject("day").getJSONObject("condition").getString("icon"),
                    maxTemp = item.getJSONObject("day").getString("maxtemp_c").toFloat().toInt().toString(),
                    minTemp = item.getJSONObject("day").getString("mintemp_c").toFloat().toInt().toString(),
                    hours = item.getJSONArray("hour").toString(),
                )
            )
        }
        list[0] = list[0].copy(
            time = mainObject.getJSONObject("current").getString("last_updated"),
            currentTemp =  mainObject.getJSONObject("current").getString("temp_c")
        )
        return list
    }
}