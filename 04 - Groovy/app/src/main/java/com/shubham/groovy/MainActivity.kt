package com.shubham.groovy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaylistFragment.newInstance()) // We're going to use .newInstance(), that's a old stuff, a recommended way to create a android fragment.
                .commit()
        }
    }
}