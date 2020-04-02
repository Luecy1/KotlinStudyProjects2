package com.example.android_testing

enum class Weather {
    SUNNY, CLOUDY, RAINY
}

class WeatherFormatter {
    fun format(weather: Weather): String {
        return "Weather is $weather"
    }

}

class WeatherForecast(
    val satellite: Satellite
) {

    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather()
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }
}

open class Satellite {
    open fun getWeather(): Weather {
        return Weather.SUNNY
    }
}

class StubSatellite(
    val anyWeather: Weather
) : Satellite() {
    override fun getWeather(): Weather {
        return anyWeather
    }
}