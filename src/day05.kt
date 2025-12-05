import util.R

fun main() {

    val input = R.getContents("day05/input.txt")

    val ranges = input
        .takeWhile { it.isNotEmpty() } // Take items till the empty line in between
        .map { line ->
            val (start, end) = line.split("-").map { it.toULong() }
            start..end
        }

    val availableIds = input
        .drop(ranges.size + 1) // Drop everything till the empty lines
        .map { it.toULong() }


    fun part1(ids: List<ULong>, ranges: List<ULongRange>) =
        ids.count { id ->
            ranges.any { range -> id in range }
        }

    println("Part 1: ${part1(availableIds, ranges)}")
}