package com.example.android_testing

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {

        val satellite = StubSatellite(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }
}