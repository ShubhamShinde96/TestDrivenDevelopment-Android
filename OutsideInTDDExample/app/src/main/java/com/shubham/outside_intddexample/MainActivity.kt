package com.shubham.outside_intddexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The system actually has nothing to do with an Android application. It is just a kotlin system of
        // two classes.

        // Therefore, the acceptance test this time is not going to be an android test that is building and
        // testing an Android application, it is simply going to be an integration test between those two classes.
        // Remember that an integration test can test can be anything from two classes up to a whole module.
        // So for this example, the integration test is going to be a unique test and not an test that is testing
        // and related things.

        // I am going to create a separate folder, one that is going to have the acceptance test and one that
        // is going to have the unit test.

        // So for this example, the integration test is going to be a JUnit test and not an android test that
        // is testing android related things.

        // I am going to create a separate folder, one that is going to have the acceptance test and one that
        // is going to have the unit test. // refer "acceptance_test" & "unit_tests" packages under "test" folder.


        // If you remember from the theory, our goal is to get through the developmental cycle as fast as
        // possible, so we should only focus on one feature at a time.
        // I am going to create a new file class with a name CarFeature.

    }


}