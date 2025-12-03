package be.swsb.aoc.day2

import be.swsb.aoc.util.readFile
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day2Test : FunSpec({
    test("part 1") {
        val input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

        solvePart1(input) shouldBe 1227775554
        solvePart1(readFile("day2/input.txt")) shouldBe 21898734247L
    }

    test("can parse all id's as longs") {
        readFile("day2/input.txt").toIdPairs()
    }

    test("id is invalid when sequence of digits repeats twice") {
        isInvalidPart1(99L) shouldBe true
        isInvalidPart1(1188511885L) shouldBe true
    }

    test("id is invalid when sequence of digits repeats at least twice") {
        isInvalidPart2(1111111L) shouldBe true
        isInvalidPart2(1111112L) shouldBe false
        isInvalidPart2(1188511885L) shouldBe true
        isInvalidPart2(123123123L) shouldBe true
    }
})

fun solvePart1(input: String) = input
    .toIdPairs()
    .toRanges()
    .keepInvalidIds(::isInvalidPart1)
    .sum()

fun isInvalidPart1(id: Long): Boolean {
    val idAsString = id.toString()
    val length = idAsString.length
    return if (length % 2 != 0) false
    else idAsString.take(length/2) == idAsString.takeLast(length/2)
}

private fun String.toIdPairs() = split(",")
    .map { idPairs -> idPairs.split("-")
        .map { n -> n.toLong() }
        .let { longs -> longs[0] to longs[1] }
    }

private fun List<Pair<Long, Long>>.toRanges() =
    map { (l,r) -> l..r }
private fun List<LongRange>.keepInvalidIds(isInvalidId: (Long) -> Boolean) =
    flatMap { idRange -> idRange.filter(isInvalidId) }

fun isInvalidPart2(id: Long): Boolean {
    val idAsString = id.toString()
    var amountToCheck = 1
    val pattern = idAsString.take(amountToCheck)
    //repeat until half id length

}