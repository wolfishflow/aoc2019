package day4

const val INPUT = "172930-683082"
const val TEST_1_INPUT = "111111"
const val TEST_2_INPUT = "223450"
const val TEST_3_INPUT = "123789"

fun main() {
    println("Test Case 1: Should return true:${isValidPassword(TEST_1_INPUT.toInt(), false)}")
    println("Test Case 2: Should return false:${isValidPassword(TEST_2_INPUT.toInt(), false)}")
    println("Test Case 3: Should return false:${isValidPassword(TEST_3_INPUT.toInt(), false)}")
    println("Part 1: The answer is ${processInput(INPUT, false)}")//1675
//    An Elf just remembered one more important detail: the two adjacent matching digits are not part of a larger group of matching digits.
    println("Part 2: The answer is ${processInput(INPUT, true)}")

}

fun processInput(input: String, onlyAllowDoubleAdjacentDuplicates: Boolean): Int {
    return getNumberOfValidPasswords(
        input.split("-").map { it.replace("-", "").toInt() },
        onlyAllowDoubleAdjacentDuplicates
    )
}

//It is a six-digit number.
//The value is within the range given in your puzzle input.
//Two adjacent digits are the same (like 22 in 122345).
//Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
//Rule one -> Prevent the number from being greater than 6 digits
//Rule two -> Don't exceed the range
//By fusing Rule 1 & 2 (Start at the first value of the range, and stop at range end)

fun getNumberOfValidPasswords(input: List<Int>, onlyAllowDoubleAdjacentDuplicates: Boolean): Int {
    val (startRange, endRange) = input.slice(0..1)
    println("Starting Password Range: $startRange, Ending Password Range: $endRange")
    var numberOfValidPasswords = 0
    var password = startRange
    while (password < endRange) {
        if (isValidPassword(password, onlyAllowDoubleAdjacentDuplicates)) {
            numberOfValidPasswords++
        }
        password++
    }
    return numberOfValidPasswords
}

fun isValidPassword(password: Int, onlyAllowDoubleAdjacentDuplicates: Boolean): Boolean {
    var containsAdjacentDuplicate = false
    val passwordIntList = password.toString().map { it.toString().toInt() }
    //The lowest previous digit can be 0, so it needs to be -1 to start with?
    var previousDigit = -1
    for (index in passwordIntList.indices) {
        when {
            //Read Right to Left -> track decrease and short circuit
            passwordIntList[index] < previousDigit -> return false
            //Adjacent Duplicate
            passwordIntList[index] == previousDigit -> containsAdjacentDuplicate = true
            else -> {
            } //do nothing?
        }
        previousDigit = passwordIntList[index]
    }
    return containsAdjacentDuplicate
}