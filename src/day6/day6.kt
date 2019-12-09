package day6

import java.io.File

const val testInput = "COM)B\n" + "B)C\n" + "C)D\n" + "D)E\n" + "E)F\n" + "B)G\n" + "G)H\n" + "D)I\n" + "E)J\n" + "J)K\n" + "K)L"

fun main() {
    //Tests
    println("Test 1: should return true: ${verifyTest(testInput, 42)}")

    println(
        "Part 1: ${calculateNumberOfOrbits(
            File("src/day6/input").readLines().map {
                it.split(")")
            }.map {
                it[1] to it[0]
            }.toMap()
        )}"
    )
}

fun verifyTest(testInput: String, numberOfOrbits: Int): Boolean {
    //Map <Parent , Children> - You can only have 1 Parent, but multiple Children
    //{B=COM, C=B, D=C, E=D, F=E, G=B, H=G, I=D, J=E, K=J, L=K}
    return calculateNumberOfOrbits(
        testInput.reader().readLines().map {
            it.split(")")
        }.map { it[1] to it[0] }.toMap()
    ) == numberOfOrbits
}

fun calculateNumberOfOrbits(orbitMap: Map<String, String>): Int {
    var numberOfOrbits = 0
    var dive = true
    var child: String
    orbitMap.map {
        child = it.value
        while (dive) {
            if (orbitMap.containsKey(child)) {
                numberOfOrbits++
                child = orbitMap[child].toString()
            } else {
                dive = false
            }
        }
        dive = true
    }
    println("calculateNumberOfOrbits: $numberOfOrbits")
    //numberOfOrbits == to the amount of all indirect orbit chains, orbitMap.size is all direct orbit chains
    return numberOfOrbits + orbitMap.size
}
