package com.example.android_testing.mock

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class WeatherForecastTest {

    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        satellite = mock(name = "MockSatellite")
        whenever(satellite.getWeather()).thenReturn(Weather.SUNNY)
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val recorder = WeatherRecorder()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }
}