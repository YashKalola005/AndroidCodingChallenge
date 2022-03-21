package com.task.demo.ui.main.viewmodel

import com.task.demo.data.api.RetrofitService
import com.task.demo.data.model.RedditResponseModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Response
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@LooperMode(LooperMode.Mode.PAUSED)
@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private val validUrl: String = "https://www.reddit.com/r/all/top/.json?t=all&limit=25"
    private val inValidUrl: String = "https://www.reddit.com/rasa/all/top/.json?t=all"

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var retrofitService: RetrofitService

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @After
    fun tearDown() {
    }

    /**
     * Check valid API call
     */
    @Test
    fun validApiCall() {
        val call: Call<RedditResponseModel?> = retrofitService.getAllDataForTest(validUrl)!!

        val response: Response<RedditResponseModel?> = call.execute()
        println("Response Code: ${response.code()}")
        assertThat(response.code()).isEqualTo(200)
    }

    /**
     * Check invalid API call
     */
    @Test
    fun inValidApiCall() {
        val call: Call<RedditResponseModel?> = retrofitService.getAllDataForTest(inValidUrl)!!

        val response: Response<RedditResponseModel?> = call.execute()
        println("Response Code: ${response.code()}")
        assertThat(response.code()).isNotEqualTo(200)
    }
}