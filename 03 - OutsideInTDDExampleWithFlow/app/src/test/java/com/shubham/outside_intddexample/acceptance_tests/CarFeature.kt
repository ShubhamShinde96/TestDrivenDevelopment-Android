package com.shubham.outside_intddexample.acceptance_tests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.shubham.outside_intddexample.Car
import com.shubham.outside_intddexample.Engine
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class CarFeature {

    private val engine = Engine()
    val car = Car(engine, 6.0)

    // here we're creating real objects and not mocking anything
    // This is a difference between the acceptance test and the Unit test, in the Unit test, as we discussed
    // previously in the theory, we are mocking all the collaborators or otherwise dependencies of all the classes that
    // are not the system under test.

    // In the acceptance test(CarFeature), we are verifying that the whole system is working. Therefore, we are not mocking
    // anything.

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @Test
    fun carIsLoosingFuelWhenItTurnsOn() = runTest {

        car.turnOn();

        // for the sake of the system, that everytime we turn on the car it is loosing half a litre of fuel.
        assertEquals(5.5, car.fuel)
    }

    // Now we have not yet created "car" class but we have created a "failing acceptance test" which is the first
    // step of "Outside-In TDD Lifecycle"
    // Once we finish the circle of "Outside-In TDD Lifecycle" we should expect this acceptance test to turn green.
    // (Refer "Outside-In TDD Lifecycle" Image)

    // ------------------------------------------------------

    // Writing second acceptance test

    @Test
    fun carIsTurningOnItsEngineAndIncreasesTheTemperatureGradually() = runTest {

        car.turnOn()

//        coroutinesTestRule.advanceTimeBy(6001)
        coroutinesTestRule.advanceTimeBy(2000)
        assertEquals(25, car.engine.temperature)

        coroutinesTestRule.advanceTimeBy(2000)
        assertEquals(50, car.engine.temperature)

        coroutinesTestRule.advanceTimeBy(2000)
        assertEquals(95, car.engine.temperature)

        // besides assertEquals() we can also use assertTrue()
        assertTrue(car.engine.isTurnedOn) // it simply checks if the boolean value is true
    }

    //  This time in order to make the acceptance test pass, we will need to run the inner cycle two times one for the
    //  Car class and one for the Engine.

    // -----------------------------------------------------------------------------------------------------------------------

    // Now after making turnOn() suspendable, I see that they have trouble even compiling.
    // We can read it in the compilation error that suspend function turnOn() should be called only from the coroutine
    // or another suspend function. Now, in order to be able to test the coroutines, we need to include them inside
    // a runBlocking or a runBlocking test code.
    // runBlocking test is still experimental, but we are going to use this one because it allows us to control the time
    // of the execution.

    // so lets go and put all code of loseFuelWhenItTurnsOn() and turnOnItsEngine() inside runBlocking (now using runTest)


    // Now the next thing that we need to do in order to test the coroutines is to include a testing rule, this rule is
    // going to assure that we are using the test dispatcher to run our coroutines, and it is also going to clean up any
    // coroutines that are left hanging after the execution of any test.
    // We'll create a new package for this which is "utils"


    // Now after adding delay in Engine turnOn(), this acceptance test is not passing as encountering following error/issue.

    // expected:<95> but was:<15>
    //  Expected :95
    //  Actual   :15

    // This is telling us that the expected temperature was 95 but it was at 15
    // assertEquals(95, car.engine.temperature) inside  carIsTurningOnItsEngineAndIncreasesTheTemperature()

}