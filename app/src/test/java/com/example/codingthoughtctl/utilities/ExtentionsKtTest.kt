package com.example.codingthoughtctl.utilities

import com.google.common.truth.Truth
import getFormattedDate
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ExtentionsKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `get formatted date with valid date in milisec`() = runTest {
        val inputDate = 1698610754
        val output = inputDate.getFormattedDate()
        Truth.assertThat(output).isEqualTo("20/01/70 09:20 pm")
    }

    @Test
    fun `get formatted date null with with null input`() = runTest {
        val inputDate = null
        val output = inputDate?.getFormattedDate()
        Truth.assertThat(output).isNull()
    }

}