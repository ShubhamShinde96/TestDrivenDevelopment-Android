package com.shubham.outside_intddexample.unit_tests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.shubham.outside_intddexample.Car
import com.shubham.outside_intddexample.Engine
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CarShould {
    // This naming convention actually comes from a great TDD practitioner called Sandra Mancuso. As we
    // explained earlier, the tests are also serving as living documentation. We should be thinking of how to
    // write our test to really explain not only what they're testing, but also what our system is doing.

    // Here, I want to remind you that we only care to verify that the car is working properly. All of its dependencies
    // should be mocked and not real objects.


    private val engine: Engine =
        mock() // mock is the object that we've not instantiated using real constructor, here mockito is creating fake object for us.
    // I am reminding you that we are out at the red unit test and we need to make it pass as fast as possible, right now
    // it has compilation issues and we the need to get it to the state that it is going to have a assertion or verification
    // issues, that is the moment that we are going to say that the test is failing for the right reason.

    // I have now created class Engine, I'm going to leave it empty, I want to write the minimum amount of code in order to
    // make that test pass.

//    private val car = Car(engine, 5.0)
    private val car : Car

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    init {
        car = Car(engine, 5.0)

        runTest {

            whenever(engine.turnOn()).thenReturn(flow {
                delay(2000)
                emit(25)
                delay(2000)
                emit(50)
                delay(2000)
                emit(95)
            })
        }

        // So right now, here, we're mocking the behaviour of Engine class and we're saying whenever we call turnOn() from
        // our mock object engine, then I want my engine mock object to return above flow result
    }

    @Test
//    fun loseFuelWhenItTurnsOn() = runBlockingTest { // runBlockingTest is deprecated so instead using runTest
    fun loseFuelWhenItTurnsOn() = runTest {

        // So now we can actually read this with the class prefix and the test name suffix
        // "CarShould LoseFuel WhenTurnsOn"
        // And it actually not only explains what the test is doing, but it also explains what the car class
        // should be doing.

        car.turnOn()

        assertEquals(4.5, car.fuel)
    }

    // Great, now we have a failing uni test, let's try to run it to prove it, even though it's pretty obvious
    // we have a unique test that does not compile.
    // Now it's time to create the minimum amount of production code to make this test pass.

    // Lets create "Car" class.

    // A very nice way to work faster is to split your screen into and have the production code on one side and the dress code on the other, so you avoid navigate between
    // the production of this code all the time.

    // Now let's go back to a diagram, we have passed through the failing acceptance test to the inner circle, the red unit test,
    // the green unit test, and now we need to go to the refactor. Now for our system I really cannot see anything that can
    // be reflected at the moment. In the real system We would have a much smaller refactoring to actually perform.


    @Test
    fun turnOnItsEngine() = runTest {

        car.turnOn()

        // And now we are going to see something that we didn't see before, this type of unit test will not assert a specific
        // outcome, it will only verify that a certain method from the mock ends in object has been called.

        verify(engine, times(1)).turnOn() //  I'm verifying that from the mock engine object
        // I called the method turnOn() one time.


        // Now after creating Engine class with turnOn(), honestly, we are going to get a lot of compilation issues
        // from the acceptance test. It's not our job to really solve them now, right now, we should be only focusing
        // on the Unit test.
        // Now, in the Real android project, this would not be an issue because the acceptance test would live under the
        // "Android test" package, not inside "test" package. Right now it's causing us some compilation issues and it is
        // not the time to fix them. Right now, we should be focusing on the inner TDD cycle, one thing at a time.
        // So now I am going to comment whole acceptance test temporarily, we are going to uncomment it when it's the right
        // time and this is when we finish within the inner TDD cycle and we need to verify if we have implemented the whole
        // functionality.

        // Output error:
        // Wanted but not invoked:
        // engine.turnOn();
        // at com.shubham.outside_intddexample.Engine.turnOn(Engine.kt:6)
        // Actually, there were zero interactions with this mock.

        // This means now the test is failing for the right reason

        // Now after fixing the issue the test passed.
        // A test passed and we are verifying that whenever we are calling the turnOn() function from our car, we are
        // calling *one time* the turn on function from our Engine.

        // change this times(1) param value to 2 and check error output.

        // Now we are at the "green" stage of lifecycle, now it's time for refactoring (Blue) stage.
        //  It is very important to not only refactor the production code, but also the test code.

        // After refactoring now we can say we have completed the inner cycle of Outside-In(WhiteBox) TDD for our
        // class car.

        // --------------------------------------------------------------------------------------------------------------------


        // Now after making turnOn() suspendable, I see that they have trouble even compiling.
        // We can read it in the compilation error that suspend function turnOn() should be called only from the coroutine
        // or another suspend function. Now, in order to be able to test the coroutines, we need to include them inside
        // a runBlocking or a runBlocking test code.
        // runBlocking test is still experimental, but we are going to use this one because it allows us to control the time
        // of the execution.

        // so lets go and put all code of loseFuelWhenItTurnsOn() and turnOnItsEngine() inside runBlocking (now using runTest)

    }

}