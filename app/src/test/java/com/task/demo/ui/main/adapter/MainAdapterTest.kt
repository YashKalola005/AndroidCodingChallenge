package com.task.demo.ui.main.adapter

import android.app.Activity
import android.widget.Filter
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(maxSdk = 32)
@RunWith(RobolectricTestRunner::class)
class MainAdapterTest {

    private var missionAdapter: MainAdapter? = null

    @Before
    fun setup() {
        println("Ready for testing!")
    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }

}