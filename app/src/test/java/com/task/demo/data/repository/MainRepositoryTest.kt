package com.task.demo.data.repository

import org.junit.Assert.*

import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(maxSdk = 32)
@RunWith(RobolectricTestRunner::class)
class MainRepositoryTest {

    @Before
    fun setup() {
        println("Ready for testing!")
    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }

}