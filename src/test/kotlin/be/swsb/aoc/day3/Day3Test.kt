package be.swsb.aoc.day3

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun `part 1`() {
        val input = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
        """.trimIndent()

        solve1(input) shouldBe 357
    }

    @Test
    fun highestJoltage() {
        "987654321111111".highestJoltage() shouldBe 98
        "811111111111119".highestJoltage() shouldBe 89
        "234234234234278".highestJoltage() shouldBe 78
        "818181911112111".highestJoltage() shouldBe 92
    }
}

fun solve1(input: String): Int {
    return input.lines().sumOf { bank ->
        0
    }
}

private fun String.highestJoltage(): Int {
    //fuck!
    return 0
}