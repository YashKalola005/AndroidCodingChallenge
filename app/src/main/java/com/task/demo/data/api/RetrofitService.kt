package com.task.demo.data.api

import com.task.demo.data.model.RedditResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface RetrofitService {

    @GET("{subreddit}/top/.json?t=all")
    suspend fun getAllData(
        @Path("subreddit") subreddit: String?,
        @Query("limit") limit: Int,
        @Query("after") after: String?,
    ): Response<RedditResponseModel>?

    @GET
    fun getAllDataForTest(@Url url: String?): Call<RedditResponseModel?>?


}