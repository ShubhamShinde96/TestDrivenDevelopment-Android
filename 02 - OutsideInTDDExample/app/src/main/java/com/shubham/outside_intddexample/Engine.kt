package com.shubham.outside_intddexample

import android.util.Log
import kotlinx.coroutines.delay

class Engine(
    var isTurnedOn: Boolean = false,
    var temperature: Int = 15
) {

     suspend fun turnOn() {
        isTurnedOn = true

//        Thread.sleep(6000)
        delay(6000)

        temperature = 95

        Log.d("TDD_COURSE", "Engine has turned on")

        // Now when you run the unit test it will give you below error:
        //      Method d in android.util.Log not mocked. See http://g.co/androidstudio/not-mocked for details.
        //      java.lang.RuntimeException: Method d in android.util.Log not mocked. See http://g.co/androidstudio/not-mocked for details.

        // This is because Log is considered as a external thing, we should have it mocked, but that's not something we really want
        // to do anything about, so in order to bypass it, we just need to come in app level build.gradle file and we need to add
        // a block of "testOptions".
        // check build.gradle file for details.
    }


}