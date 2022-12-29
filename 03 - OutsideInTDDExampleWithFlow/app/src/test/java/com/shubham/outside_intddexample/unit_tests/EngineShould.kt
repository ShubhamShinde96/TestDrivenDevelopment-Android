package com.shubham.outside_intddexample.unit_tests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.shubham.outside_intddexample.Engine
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class EngineShould {

    // Since we're practicing, test driven development, we want to focus more on the test and the later on the development.

//    private val engine = Engine(false, 15)
    private val engine = Engine() // Assigned default values inside constructor

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @Test
    fun turnOn() = runTest {
        engine.turnOn()

        assertTrue(engine.isTurnedOn)
    }

    @Test
    fun riseTemperatureGraduallyWhenItTurnsOn() = runTest {

        val flow = engine.turnOn()
        val actual = flow.toList()

        assertEquals(listOf(25, 50, 95), actual) // checking flow emitted values are correct
    }

}