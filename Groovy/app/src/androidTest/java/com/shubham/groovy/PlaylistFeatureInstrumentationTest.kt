package com.shubham.groovy

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PlaylistFeatureInstrumentationTest {

    // Writing first espresso test

    // The first thing that we need to do is to tell our test to target our "MainActivity".

    val mActivityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get // Now we have told the espresso that it needs to initialise and kill the activity
    // after the test has finished.



    @Test // Test is going to check if we're displaying "Playlist" as title on the actionbar.
    fun displayScreenTitle() {

        assertDisplayed("Playlist")
    }
}