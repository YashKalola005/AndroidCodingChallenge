package com.task.demo.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import com.task.demo.data.api.RetrofitService
import com.task.demo.data.model.RedditResponseDtoMapper
import com.task.demo.utils.Constants
import com.task.demo.utils.MyPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Preference {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): MyPreferences {
        return MyPreferences(context, context.getSharedPreferences("appKey", Context.MODE_PRIVATE))

    }


}
