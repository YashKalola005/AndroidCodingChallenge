package com.task.demo.ui.main.view

import android.content.Intent
import android.os.Looper
import android.os.Looper.getMainLooper
import android.view.View
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.google.gson.Gson
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.ui.main.viewmodel.DetailsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import java.io.IOException
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@LooperMode(LooperMode.Mode.PAUSED)
@RunWith(RobolectricTestRunner::class)
class DetailsActivityTest {

    private var activity: DetailsActivity? = null
    private lateinit var activityController: ActivityController<DetailsActivity>

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        activityController = Robolectric.buildActivity(DetailsActivity::class.java)
        activity = activityController.create().get()

    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }

    /**
     * Check visibility of post attributes
     */
    @Test
    fun checkVisibility() {
        assertThat(activity?.binding?.title?.visibility ?: View.GONE == View.VISIBLE).isEqualTo(true)
        assertThat(activity?.binding?.author?.visibility ?: View.GONE == View.VISIBLE).isEqualTo(true)
        assertThat(activity?.binding?.comments?.visibility ?: View.GONE == View.VISIBLE).isEqualTo(true)
        assertThat(activity?.binding?.date?.visibility ?: View.GONE == View.VISIBLE).isEqualTo(true)
        assertThat(activity?.binding?.image?.visibility ?: View.GONE == View.VISIBLE).isEqualTo(true)

    }

    /**
     * Check time calculation
     */
    @Test
    fun checkValidTimeCalculation() {
        shadowOf(getMainLooper()).idle()
        val hours = viewModel.getTimeInHours(7200000.0)
        assertThat(hours).isEqualTo("2 hours ago")
    }

    @Test
    fun checkInValidTimeCalculation() {
        shadowOf(getMainLooper()).idle()
        val hours = viewModel.getTimeInHours(14400000.0)
        assertThat(hours).isNotEqualTo("2 hours ago")
    }
}