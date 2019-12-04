package day1

import java.io.File
import kotlin.math.floor

/**
 * Fuel required to launch a given module is based on its mass.
 * Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
 *
 * Formula = (Rnd.Floor(input / 3)) - 2
 */
fun main() {
    var totalFuelRequired = 0
    File("src/day1/input").forEachLine {
        totalFuelRequired += calculateFuelRequired(it.toDouble())
    }
    println(totalFuelRequired)
}

fun calculateFuelRequired(mass : Double ) : Int {
    return (floor(mass / 3) - 2).toInt()
}
