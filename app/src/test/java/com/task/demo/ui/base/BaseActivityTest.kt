package com.task.demo.ui.base

import org.junit.Assert.*

import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
class BaseActivityTest  {
    @Before
    fun setup() {
        println("Ready for testing!")
    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }
}