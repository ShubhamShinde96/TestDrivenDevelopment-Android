package com.shubham.firstunittest

class Engine(
    val cc: Int, // cc: cubic centimeter
    val horsePower: Int,
    var temperature: Int,
    var isTurnedOn: Boolean
) {

    fun turnOn() {
        isTurnedOn = true
        temperature = 95
    }

    fun turnOff() {
        isTurnedOn = false
        temperature = 15
    }

    // Now we're going to verify the behaviour of turnOn() function with the help of Unit Test

}