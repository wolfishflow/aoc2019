package day8

import java.io.File


const val TEST_1_INPUT = "123456789012"
const val TEST_1_WIDE_PARAM = 3
const val TEST_1_TALL_PARAM = 2

const val WIDE_PARAM = 25
const val TALL_PARAM = 6

fun main() {

    //Tests
    //123456 where 1(1) * 1(2) = 1
    println("Test 1: ${parseInput(TEST_1_INPUT, TEST_1_WIDE_PARAM * TEST_1_TALL_PARAM) == 1}")
    println("Part 1: ${parseInput(File("src/day8/input").readText(), WIDE_PARAM * TALL_PARAM)}")
}

fun parseInput(input: String, layerArea: Int) : Int {
    var numberOfOnes = 0
    var numberOfTwos = 0
    //minBy - needs a function, which will return the smallest value (in this case int ) based on the condition
    input.chunked(layerArea).minBy { getLayerWithLowestAmountOfZeroes(it) }?.forEach {
        when (it) {
            '1' -> numberOfOnes++
            '2' -> numberOfTwos++
        }
    }
    return numberOfOnes * numberOfTwos
}

fun getLayerWithLowestAmountOfZeroes(inputLayer: String): Int {
    //filter syntax - argument -> condition
    return inputLayer.filter { string -> string == '0' }.length
}