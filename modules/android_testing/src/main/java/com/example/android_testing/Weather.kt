package com.example.android_testing

import android.service.autofill.TextValueSanitizer

enum class Weather {
    SUNNY, CLOUDY, RAINY
}

class WeatherFormatter {
    fun format(weather: Weather): String {
        return "Weather is $weather"
    }

}

class WeatherForecast(
    val satellite: Satellite,
    val recorder: WeatherRecorder,
    val formatter: WeatherFormatter
) {
    fun recordCurrentWeather() {
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }
}

class WeatherRecorder {

    fun record(weather: String) {

    }

}

class Satellite {

    fun getWeather(): Weather {
        return Weather.SUNNY
    }

}
