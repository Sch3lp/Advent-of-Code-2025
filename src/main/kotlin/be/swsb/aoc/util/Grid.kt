package be.swsb.aoc.util

fun parseToGrid(input: String): Map<Point, Char> = input.lines()
    .flatMapIndexed { y, line ->
        line.mapIndexed { x, character ->
            Point(x, y) to character
        }
    }.toMap()