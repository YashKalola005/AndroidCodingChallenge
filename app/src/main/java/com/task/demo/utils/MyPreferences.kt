package com.task.demo.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Class MyPreferences
 * Description: Used for storing app preferences locally
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-22
 */


class MyPreferences  constructor(@ApplicationContext context : Context,pref: SharedPreferences) {

    private var pref = pref

    fun setBoolean(key: String?, value: Boolean) {
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String?): Boolean {
        return pref.getBoolean(key, false)
    }

}