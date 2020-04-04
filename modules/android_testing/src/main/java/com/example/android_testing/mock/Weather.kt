package com.example.android_testing.mock

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

    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather()
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather() {
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
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

open class WeatherRecorder {

    private val record = mutableListOf<String>()

    open fun record(weather: String) {
        record.add(weather)
    }

    override fun toString(): String {
        return record.joinToString("\n")
    }
}
