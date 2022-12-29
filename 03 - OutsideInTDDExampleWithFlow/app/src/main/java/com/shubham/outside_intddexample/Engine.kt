package com.shubham.outside_intddexample

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Engine(
    var isTurnedOn: Boolean = false,
    var temperature: Int = 15
) {

     suspend fun turnOn(): Flow<Int> {
        isTurnedOn = true

//        Thread.sleep(6000)
//        delay(6000)

         // So now we want to increase the temperature gradually after 6 second delay


         return flow {
             delay(2000)
             temperature = 25
             emit(temperature)

             delay(2000)
             temperature = 50
             emit(temperature)

             delay(2000)
             temperature = 95
             emit(temperature)

             Log.d("TDD_COURSE", "Engine has turned on")
         }


    }


}