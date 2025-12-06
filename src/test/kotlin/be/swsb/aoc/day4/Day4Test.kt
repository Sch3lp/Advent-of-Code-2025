package be.swsb.aoc.day4

import be.swsb.aoc.util.Point
import be.swsb.aoc.util.parseToGrid
import be.swsb.aoc.util.readFile
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {
    @Test
    fun `part 1`() {
        val input = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
        """.trimIndent()

        solve1(input) shouldBe 13
        solve1(readFile("day4/input.txt")) shouldBe 1349
    }

    @Test
    fun `part 2`() {
        val input = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
        """.trimIndent()

        solve2(input) shouldBe 43
        solve2(readFile("day4/input.txt")) shouldBe 8277
    }
}

private fun canBeRemoved(char: Char, at: Point, wall: Map<Point, Char>): Boolean =
    char == '@' && at.neighbours.map { wall[it] }.count { it == '@' } < 4

fun solve1(input: String): Int {
    val wall = parseToGrid(input)
    return wall.count { (at,char) -> canBeRemoved(char,at,wall) }
}

fun solve2(input: String): Int {
    val wall = parseToGrid(input)
    tailrec fun loop(wall: Map<Point, Char>, count: Int): Int {
        val removed = wall.filter { (at, char) -> canBeRemoved(char, at, wall) }
        return if (removed.isNotEmpty()) loop(wall - removed.keys, count + removed.size)
        else count
    }
    return loop(wall, 0)
}

