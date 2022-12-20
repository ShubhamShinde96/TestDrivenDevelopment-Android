package com.shubham.firstunittest

import org.junit.Test

import org.junit.Assert.*

class EngineTest {

    private val engine: Engine = Engine(2000, 189, 15, false)

    @Test   // name of function is IMP, it must tell what it is going to test
    fun engineTurnsOn() {

        engine.turnOn()

        assertEquals(true, engine.isTurnedOn)
        // The assertEquals() is coming from "JUnit" library.
        // The assertEquals() fun make sure that the actual outcome is equal with the expected outcome.

        assertEquals(95, engine.temperature) // try putting 90 instead of 95
    }

    // Assignment
    @Test
    fun engineTurnsOff() {

        engine.turnOn()
        engine.turnOff()
        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }


}