package com.task.demo.ui.main.view

import android.view.View
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.task.demo.databinding.ActivityMainBinding
import com.task.demo.ui.main.adapter.MainAdapter
import com.task.demo.ui.main.viewmodel.MainViewModel
import com.task.demo.ui.main.viewmodel.MainViewModelTest
import com.task.demo.utils.Constants
import com.task.demo.utils.MyPreferences
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
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
        assertThat((activity?.binding?.recyclerView)?.visibility ?: View.GONE == View.VISIBLE).isEqualTo(
            true
        )

    }


}