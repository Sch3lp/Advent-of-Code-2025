package be.swsb.aoc2024.day1

import be.swsb.aoc2024.util.readFile
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.time.measureTime


class Day1Test : FunSpec({

    test("part 1") {
        val input = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent()

        measureTime { solvePart1(input) shouldBe 3 }.also { println(it) }
        measureTime { solvePart1(readFile("day1/input.txt")) shouldBe 1180 }.also { println(it) }

        measureTime { solvePart1V2(input) shouldBe 3 }.also { println(it) }
        measureTime { solvePart1V2(readFile("day1/input.txt")) shouldBe 1180 }.also { println(it) }
    }

    test("part 2") {
        val input = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent()

        measureTime { solvePart2(input) shouldBe 6 }.also { println(it) }
        measureTime { solvePart2(readFile("day1/input.txt")) shouldBe 1180 }.also { println(it) }
    }
})

fun solvePart1V2(input: String): Int {
    var amountOfTimesAt0 = 0
    val dial = MutableDial(50)
    for (line in input.lines()) {
        if (line.startsWith("L")) dial.left(line.drop(1).toInt())
        else dial.right(line.drop(1).toInt())
        if (dial.arrow == 0) amountOfTimesAt0++
    }
    return amountOfTimesAt0
}

fun solvePart1(input: String): Int {
    val dial = input.lines().fold(Dial(arrow = 50)) { acc, instr ->
        val turnedDial = if (instr.startsWith("L")) acc.left(instr.drop(1).toInt())
        else acc.right(instr.drop(1).toInt())
        turnedDial
            .let { dial -> if (dial.arrow == 0) dial.copy(amountOfTimesAt0 = dial.amountOfTimesAt0+1) else dial }
    }
    return dial.amountOfTimesAt0
}


fun solvePart2(input: String): Int {
    val dial = input.lines().fold(Dial2(arrow = 50)) { acc, instr ->
        if (instr.startsWith("L")) acc.left(instr.drop(1).toInt())
        else acc.right(instr.drop(1).toInt())
    }
    return dial.amountOfTimesAt0
}

class MutableDial(arrow: Int) {
    var arrow: Int = arrow
        private set
    fun left(distance: Int) {
        arrow = (arrow - distance).mod(100)
    }
    fun right(distance: Int) {
        arrow = (arrow + distance).mod(100)
    }
}

data class Dial(val arrow: Int, val amountOfTimesAt0: Int = 0) {
    fun left(distance: Int): Dial = copy(arrow = (arrow - distance).mod(100))
    fun right(distance: Int): Dial = copy(arrow = (arrow + distance).mod(100))
}

data class Dial2(val arrow: Int, val amountOfTimesAt0: Int = 0) {
    fun left(distance: Int): Dial2 = (1..distance).fold(this) { acc, _ -> acc.copy(arrow = (acc.arrow - 1).mod(100)).password() }
    fun right(distance: Int): Dial2 = (1..distance).fold(this) { acc, _ -> acc.copy(arrow = (acc.arrow + 1).mod(100)).password() }
    fun password() = (if (arrow == 0) copy(amountOfTimesAt0 = amountOfTimesAt0 + 1) else this)
}
