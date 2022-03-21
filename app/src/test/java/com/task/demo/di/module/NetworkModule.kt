package com.task.demo.di.module


import com.google.gson.GsonBuilder
import com.task.demo.data.api.RetrofitService
import com.task.demo.data.model.RedditResponseDtoMapper
import com.task.demo.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRedditResponseMapper(): RedditResponseDtoMapper {
        return RedditResponseDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRedditService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)
    }

}