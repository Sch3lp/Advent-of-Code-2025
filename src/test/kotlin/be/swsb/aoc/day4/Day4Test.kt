package be.swsb.aoc.day4

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
}

fun solve1(input: String): Int {
    val wall = parseToGrid(input)
    return wall.count { (at,char) ->
        char == '@' && at.neighbours.map { wall[it] }.count { it == '@' } < 4
    }
}