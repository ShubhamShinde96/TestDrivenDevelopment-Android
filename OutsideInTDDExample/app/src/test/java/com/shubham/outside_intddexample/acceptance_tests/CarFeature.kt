package com.shubham.outside_intddexample.acceptance_tests

import com.shubham.outside_intddexample.Car
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CarFeature {

    val car = Car(6.0)

    @Test
    fun carIsLoosingFuelWhenItTurnsOn() {

        car.turnOn();

        // for the sake of the system, that everytime we turn on the car it is loosing half a litre of fuel.
        assertEquals(5.5, car.fuel)
    }

    // Now we have not yet created "car" class but we have created a "failing acceptance test" which is the first
    // step of "Outside-In TDD Lifecycle"
    // Once we finish the circle of "Outside-In TDD Lifecycle" we should expect this acceptance test to turn green.
    // (Refer "Outside-In TDD Lifecycle" Image)

}