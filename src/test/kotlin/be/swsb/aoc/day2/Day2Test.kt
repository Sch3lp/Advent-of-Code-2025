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
        isInvalid(99L) shouldBe true
        isInvalid(1188511885L) shouldBe true
    }
})

fun solvePart1(input: String) = input
    .toIdPairs()
    .toRanges()
    .keepInvalidIds()
    .sum()

private fun List<Pair<Long, Long>>.toRanges() = map { (l,r) -> l..r }

private fun List<LongRange>.keepInvalidIds() = flatMap { idRange -> idRange.filter(::isInvalid) }

fun isInvalid(id: Long): Boolean {
    val idAsString = id.toString()
    val length = idAsString.length
    return if (length % 2 != 0) false
    else idAsString.take(length/2) == idAsString.takeLast(length/2)
}

fun String.toIdPairs() = split(",")
    .map { idPairs -> idPairs.split("-")
        .map { n -> n.toLong() }
        .let { longs -> longs[0] to longs[1] }
    }