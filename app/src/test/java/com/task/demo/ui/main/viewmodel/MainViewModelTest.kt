package com.task.demo.ui.main.viewmodel

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.task.demo.data.api.RetrofitService
import com.task.demo.data.model.RedditResponseModel

import org.junit.Assert.*

import junit.framework.TestCase
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response

@Config(maxSdk = 32)
@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private val url: String = "https://www.reddit.com/r/all/top/.json?t=all&limit=25"
    private val mockViewModelTest = mock<MainViewModel>()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    suspend fun givenTheApiResponseIsOK_whenExecuteIsCalled_thenApiResultOKIsReturned() {
        val mockApiRequest = mock<RetrofitService>()
        val mockApiResponse = mock<Response<RedditResponseModel>>()
        whenever(mockApiRequest.getAllData(url)).thenReturn(
            mockApiResponse
        )
        whenever(mockApiResponse.isSuccessful).thenReturn(true)
    }


}