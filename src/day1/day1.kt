package day1

import java.io.File
import kotlin.math.floor

/**
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
println(totalFuelRequired) //3263320
}

fun calculateFuelRequired(mass : Double ) : Int {
return (floor(mass / 3) - 2).toInt()
}
 **/

/**
 * Fuel itself requires fuel just like a module - take its mass, divide by three, round down, and subtract 2.
 * However, that fuel also requires fuel, and that fuel requires fuel, and so on.
 * Any mass that would require negative fuel should instead be treated as if it requires zero fuel;
 * the remaining mass, if any, is instead handled by wishing really hard, which has no mass and is outside the scope of this calculation.
 */
fun main() {
    var totalFuelRequiredForModules = 0
    var totalFuelRequiredForModulesAndFuel = 0
    //TODO refactor to use readLines + map?
    File("src/day1/input").forEachLine {
        val calculatedModuleFuel = calculateFuelRequiredForModule(it.toInt())
        val calculatedFuelForFuel = calculateFuelRequiredForFuel(calculatedModuleFuel)
        totalFuelRequiredForModules += calculatedModuleFuel
        totalFuelRequiredForModulesAndFuel += calculatedModuleFuel + calculatedFuelForFuel
    }
    println("Test Cases: Module Fuel")
    println("Mass of 12; Fuel should be 2: ${checkFuelAgainstModuleMass(2, 12)}")
    println("Mass of 14; Fuel should be 2: ${checkFuelAgainstModuleMass(2, 14)}")
    println("Mass of 1969; Fuel should be 654: ${checkFuelAgainstModuleMass(654, 1969)}")
    println("Mass of 100756; Fuel should be 33583: ${checkFuelAgainstModuleMass(33583, 100756)}")

    println("Sum of Module Fuel: $totalFuelRequiredForModules") //3263320

    println("Test Cases: Fuel for Fuel")
    println("Fuel for Fuel - 14 mass require 2 fuel which require 2 fuel: ${checkMassAgainstFuelForFuel(14, 2)}")
    println("Fuel for Fuel - 1969 mass requires 654  requires 966: ${checkMassAgainstFuelForFuel(1969, 966)}")
    println("Fuel for Fuel - 100756 mass requires 33583 requires 50346: ${checkMassAgainstFuelForFuel(100756, 50346)}")

    println("Sum of all Fuel required : $totalFuelRequiredForModulesAndFuel")
}

fun calculateFuelRequiredForModule(mass: Int): Int {
    return (mass / 3) - 2
}

fun calculateFuelRequiredForFuel(fuelMass: Int): Int {
    var sumOfCalculateFuel = 0
    var fuelRequiredForFuel = fuelMass
    while (fuelRequiredForFuel > 0) {
        fuelRequiredForFuel = (fuelRequiredForFuel / 3) - 2
        if (fuelRequiredForFuel > 0) {
            sumOfCalculateFuel += fuelRequiredForFuel
        }
    }
    return sumOfCalculateFuel
}


//Test Cases Method
fun checkFuelAgainstModuleMass(expectedFuel: Int, mass: Int) : Boolean {
    return calculateFuelRequiredForModule(mass) == expectedFuel
}

fun checkMassAgainstFuelForFuel(mass: Int, expectedFuel: Int) : Boolean {
    val moduleFuel = calculateFuelRequiredForModule(mass)
    return moduleFuel + calculateFuelRequiredForFuel(moduleFuel) == expectedFuel
}




