package com.task.demo.data.repository

import com.task.demo.data.api.RetrofitService

/**
 * Class MainRepository
 * Description: Calls the reddit API using RetrofitService
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllData(endpoint: String?) = retrofitService.getAllData(endpoint)

}