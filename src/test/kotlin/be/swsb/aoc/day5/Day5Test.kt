package be.swsb.aoc.day5

import be.swsb.aoc.util.readFile
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day5Test {
    @Test
    fun `part 1`() {
        val input = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """.trimIndent()

        solve1(input) shouldBe 3
        solve1(readFile("day5/input.txt")) shouldBe 862
    }
}

fun solve1(input: String): Int {
    val (freshIngredientIds, ingredients) = parse(input)
    return ingredients.count { ingredientId -> freshIngredientIds.any { ingredientId in it } }
}

private fun parse(input: String): Pair<List<LongRange>, List<Long>> {
    val lines = input.lines()
    val breakIdx = lines.indexOf("")
    val freshIngredientIds = lines.subList(0, breakIdx)
    val freshIngredientIdRanges = freshIngredientIds.map { it.split("-").let { (l,r) -> LongRange(l.toLong(), r.toLong()) } }
    return freshIngredientIdRanges to lines.subList(breakIdx+1, lines.size).map { it.toLong() }
}