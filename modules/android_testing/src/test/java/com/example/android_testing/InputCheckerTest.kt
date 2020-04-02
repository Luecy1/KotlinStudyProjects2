package com.example.android_testing

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

// 省略可能、省略した場合、@RunWith(JUnit4::class)と同じｚ
@RunWith(JUnit4::class)
class InputCheckerTest {

    lateinit var target: InputChecker

    @Before
    fun setUp() {
        target = InputChecker()
    }

    @Test
    fun isValid() {
        val actual = target.isValid("foo")
        assertThat(actual).isTrue()
    }

    @Test
    fun isValid_givenLessThan3_returnFalse() {
        val actual = target.isValid("ab")
        assertThat(actual).isFalse()
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        val actual = target.isValid("abc123")
        assertThat(actual).isTrue()
    }

    @Test(expected = IllegalArgumentException::class)
    fun isValid_givenNull_throwsIllegalArgumentException() {
        target.isValid(null)
    }
}