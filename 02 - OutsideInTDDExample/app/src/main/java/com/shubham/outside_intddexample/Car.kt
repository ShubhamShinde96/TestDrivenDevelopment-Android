package com.shubham.outside_intddexample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Car(
    val engine: Engine,
    var fuel: Double
    ) {

    fun turnOn() {

        fuel -= 0.5

//        engine.turnOn()

        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn()
        }
    }

}




