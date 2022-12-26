package com.shubham.outside_intddexample.unit_tests

import com.shubham.outside_intddexample.Car
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CarShould {
    // This naming convention actually comes from a great TDD practitioner called Sandra Mancuso. As we
    // explained earlier, the tests are also serving as leaving the documentation we should be thinking of how to
    // write our test to really explain not only what they're testing, but also what our system is doing.

    private val car = Car(5.0)

    @Test
    fun loseFuelWhenItTurnsOn() {

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

}