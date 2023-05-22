package com.shubham.groovy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This time, the acceptance test for our application is going to be "Android Instrumentation Test" or
        // otherwise called "Espresso Test". Those tests are going to live inside "AndroidTest" folder.
        // For these tests we're going to use idling-resource, test:runner, junit, espresso-core, spain:barista
        // barista will make our life easier when validating android views
        // These tests actually build, run and test the whole application like a normal human would do.

        // Let's write our first espresso test

    }


}