package com.task.demo.ui.main.viewmodel

import android.os.Looper
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@LooperMode(LooperMode.Mode.PAUSED)
@RunWith(RobolectricTestRunner::class)
class DetailsViewModelTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    /**
     * Check time calculation
     */

    @Test
    fun checkValidTimeCalculation() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val hours = getTimeInHours(7200000.0)
        assertThat(hours).isEqualTo("2 hours ago")
    }

    @Test
    fun checkInValidTimeCalculation() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val hours = getTimeInHours(14400000.0)
        assertThat(hours).isNotEqualTo("2 hours ago")
    }

    /**
     * Converts milliseconds into hours
     */
    private fun getTimeInHours(created: Double): CharSequence {
        var hours = ((created / (1000 * 60 * 60)) % 24).toString()
        hours = hours.substringBefore(".")
        return "$hours hours ago"

    }
}