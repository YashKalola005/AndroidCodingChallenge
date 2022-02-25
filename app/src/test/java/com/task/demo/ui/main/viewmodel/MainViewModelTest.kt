package com.task.demo.ui.main.viewmodel


import com.nhaarman.mockitokotlin2.mock
import com.task.demo.data.api.RetrofitService
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.ui.main.view.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo


@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private var retrofitService = mock<RetrofitService>()
    private val validUrl: String = "https://www.reddit.com/r/all/top/.json?t=all&limit=25"
    private val inValidUrl: String = "https://www.reddit.com/rasa/all/top/.json?t=all"


    @Before
    fun setUp() {
        retrofitService = RetrofitService.getInstance()
    }

    @After
    fun tearDown() {
    }


    /**
     * Check valid API call
     */
    @Test
    fun validApiCall() {
        var call: Call<RedditResponseModel?> = retrofitService.getAllDataForTest(validUrl)!!

        val response: Response<RedditResponseModel?> = call.execute()
        assertThat(response.code()).isEqualTo(200)
    }

    /**
     * Check invalid API call
     */
    @Test
    fun inValidApiCall() {
        var call: Call<RedditResponseModel?> = retrofitService.getAllDataForTest(inValidUrl)!!

        val response: Response<RedditResponseModel?> = call.execute()
        println("Response Code: ${response.code()}")
        assertThat(response.code()).isNotEqualTo(200)
    }
}