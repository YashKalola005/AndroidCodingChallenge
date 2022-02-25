package com.task.demo.ui.main.view

import android.view.View
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private var activity: MainActivity? = null
    private var activityController: ActivityController<MainActivity>? = null

    @Before
    fun setup() {
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activity = activityController?.create()?.start()?.get()
    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }

    /**
     * Check visibility of recycleView
     */
    @Test
    fun click() {
        assertThat((activity?.binding?.recyclerView)?.visibility ?: View.GONE == View.VISIBLE).isEqualTo(true)

    }


}