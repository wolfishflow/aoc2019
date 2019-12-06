package day2

import java.io.File

/**
 * An Intcode program is a list of integers separated by commas (like 1,0,0,3,99).
 * To run one, start by looking at the first integer (called position 0).
 * Here, you will find an opcode - either 1, 2, or 99.
 * The opcode indicates what to do; for example, 99 means that the program is finished and should immediately halt.
 * Encountering an unknown opcode means something went wrong.
 *
 * Opcode 1 adds together numbers read from two positions and stores the result in a third position.
 * The three integers immediately after the opcode tell you these three positions -
 * the first two indicate the positions from which you should read the input values, and the third indicates the position at which the output should be stored.
 *
 * For example, if your Intcode computer encounters 1,10,20,30, it should read the values at positions 10 and 20, add those values,
 * and then overwrite the value at position 30 with their sum.
 *
 * Opcode 2 works exactly like opcode 1, except it multiplies the two inputs instead of adding them.
 * Again, the three integers after the opcode indicate where the inputs and outputs are, not their values.
 *
 * Once you're done processing an opcode, move to the next one by stepping forward 4 positions.
 */

const val ADDITION = 1
const val MULTIPLY = 2
const val TERMINATE = 99
const val NUMBER_OF_VALUES = 4
const val OBJECTIVE = 19690720

const val test1Input = "1,9,10,3,2,3,11,0,99,30,40,50"
const val test1Result = "3500,9,10,70,2,3,11,0,99,30,40,50"

const val test2Input = "1,0,0,0,99"
const val test2Result = "2,0,0,0,99"

const val test3Input = "2,3,0,3,99"
const val test3Result = "2,3,0,6,99"

const val test4Input = "2,4,4,5,99,0"
const val test4Result = "2,4,4,5,99,9801"

const val test5Input = "1,1,1,4,99,5,6,0,99"
const val test5Result = "30,1,1,4,2,5,6,0,99"

fun main() {
    println("Running Tests:")
    checkInput(
        test1Input.split(",").map { it.toInt() }.toIntArray(),
        test1Result.split(",").map { it.toInt() }.toIntArray()
    )
    checkInput(
        test2Input.split(",").map { it.toInt() }.toIntArray(),
        test2Result.split(",").map { it.toInt() }.toIntArray()
    )
    checkInput(
        test3Input.split(",").map { it.toInt() }.toIntArray(),
        test3Result.split(",").map { it.toInt() }.toIntArray()
    )
    checkInput(
        test4Input.split(",").map { it.toInt() }.toIntArray(),
        test4Result.split(",").map { it.toInt() }.toIntArray()
    )
    checkInput(
        test5Input.split(",").map { it.toInt() }.toIntArray(),
        test5Result.split(",").map { it.toInt() }.toIntArray()
    )

    println("Running Day 2: ")
    val commandInputs = File("src/day2/input").readText().split(",").map {
        it.trim().toInt()
    }.toIntArray()
//    replace position 1 with the value 12
    commandInputs[1] = 12
//    replace position 2 with the value 2
    commandInputs[2] = 2
//    What value is left at position 0
    println("Part 1: ${initializeWithCommandInputs(commandInputs.copyOf())[0]}") //4090701
    println("Part 2: ${determineNounAndVerb(commandInputs.copyOf())}")//6421
}


fun checkInput(commandsIntArray: IntArray, results: IntArray) {
    print("Input: ${commandsIntArray.asList()}, Result: ${results.asList()} ; ")
    println("Did Test Pass: ${initializeWithCommandInputs(commandsIntArray).contentEquals(results)}")
}

fun initializeWithCommandInputs(commandsIntArray: IntArray): IntArray {
    for (commandInput in commandsIntArray.indices step NUMBER_OF_VALUES) {
        val instruction = commandsIntArray[commandInput]
        if (instruction == TERMINATE) {
            break
        }
        try {
            val (param1, param2, param3) = commandsIntArray.slice(commandInput + 1..commandInput + 3)
            runGravityAssistProgram(commandsIntArray, instruction, param1, param2, param3)
        } catch (e: ArrayIndexOutOfBoundsException) {
            break
        }
    }
    return commandsIntArray
}

fun runGravityAssistProgram(commandsIntArray: IntArray, commandCode: Int, param1: Int, param2: Int, param3: Int) {
    when (commandCode) {
        ADDITION -> commandsIntArray[param3] = (commandsIntArray[param1] + commandsIntArray[param2])
        MULTIPLY -> commandsIntArray[param3] = (commandsIntArray[param1] * commandsIntArray[param2])
    }
}

fun determineNounAndVerb(commandInputs: IntArray): Int {
    for (noun in 0 until 99) {
        for (verb in 0 until 99) {
            val copyOfCommandInput = commandInputs.copyOf()
            copyOfCommandInput[1] = noun
            copyOfCommandInput[2] = verb
            initializeWithCommandInputs(copyOfCommandInput)
            if (copyOfCommandInput[0] == OBJECTIVE) {
                return 100 * noun + verb
            }
        }
    }
    return -1
}
