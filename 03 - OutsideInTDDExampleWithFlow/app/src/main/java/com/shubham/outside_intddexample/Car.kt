package com.shubham.outside_intddexample

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Car(
    val engine: Engine,
    var fuel: Double
    ) {

    fun turnOn() {

        fuel -= 0.5

//        engine.turnOn()

        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn().collect { temperature ->
                Log.d("TDD_COURSE", "Collected engine temperature: $temperature")
            }
        }
    }

}




