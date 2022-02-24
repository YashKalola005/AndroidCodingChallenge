package com.task.demo.ui.main.view

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    private var mActivity: MainActivity? = null
    private var activityController: ActivityController<MainActivity>? = null

    @Before
    fun setup() {
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        println("Ready for testing!")
    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }




    @Test
    fun click() {

    }



}