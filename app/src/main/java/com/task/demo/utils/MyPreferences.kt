package com.task.demo.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Class MyPreferences
 * Description: Used for storing app preferences locally
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-22
 */
class MyPreferences(context: Context) {


    private lateinit var pref: SharedPreferences
    private var parentActivity: Context? = context
    private var appKey: String? = null

    init {
        appKey = context.packageName.replace("\\.".toRegex(), "_").toLowerCase()
    }

    fun setString(key: String?, value: String?) {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        return pref.getString(key, "")
    }


    fun setDouble(key: String?, value: Double) {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(key, value.toString() + "")
        editor.apply()
    }

    fun getDouble(key: String?): Double? {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        return if (pref.getString(key, "")!!.length > 0) {
            pref.getString(key, "")!!.toDouble()
        } else {
            null
        }
    }

    fun setBoolean(key: String?, value: Boolean) {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String?): Boolean {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        return pref.getBoolean(key, false)
    }


    fun setInt(key: String?, value: Int) {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String?): Int {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        return pref.getInt(key, 0)
    }

    fun setLong(key: String?, value: Long) {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String?): Long {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        return pref.getLong(key, 0)
    }

    fun is_exist(key: String?): Boolean {
        pref = parentActivity!!.getSharedPreferences(
            appKey,
            Context.MODE_PRIVATE
        )
        return pref.contains(key)
    }

    fun clearData(context: Context) {
        val settings = context.getSharedPreferences(appKey, Context.MODE_PRIVATE)
        settings.edit().clear().apply()
    }

}