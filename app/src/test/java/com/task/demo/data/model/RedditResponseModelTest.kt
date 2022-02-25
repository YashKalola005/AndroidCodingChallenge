package com.task.demo.data.model

import android.database.Cursor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import com.task.demo.ui.main.view.MainActivity
import org.junit.After
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
class RedditResponseModelTest {
    private val mocResponseModel = mock<RedditResponseModel>()

    @Before
    fun setup() {
        reset(mocResponseModel)
        val missionResponse = mocResponseModel.getData1()?.children
        missionResponse?.let {
            val data = it[0].data
            data?.title = "Ever since my niece saw Toy Story"
            data?.author = "finestjun"
            data?.numComments = 2113
            data?.created = 1577652148.0
            data?.url = "https://i.redd.it/nbf6kvcbwm741.jpg"
            data?.thumbnail =
                "https://b.thumbs.redditmedia.com/T0zVXdl5mDoFCZvhv4moqQueQIMXMRL8qEJzH0rjG6Q.jpg"
        }

        println("Ready for testing!")
    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun initializesTheRedditResponse() {
        val missionResponse = mocResponseModel.getData1()?.children


        missionResponse?.let {
            val data = it[0].data
            assertThat(data?.title).isEqualTo("Ever since my niece saw Toy Story")
            assertThat(data?.author).isEqualTo("finestjun")
            assertThat(data?.numComments).isEqualTo("2113")
            assertThat(data?.created).isEqualTo("1577652148.0")
            assertThat(data?.url).isEqualTo("https://i.redd.it/nbf6kvcbwm741.jpg")
            assertThat(data?.thumbnail).isEqualTo("https://b.thumbs.redditmedia.com/T0zVXdl5mDoFCZvhv4moqQueQIMXMRL8qEJzH0rjG6Q.jpg")
        }


    }

}