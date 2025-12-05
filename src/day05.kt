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

    fun mergeRanges(ranges: List<ULongRange>): List<ULongRange> {
        if (ranges.isEmpty()) return emptyList()

        val sortedRanges = ranges.sortedBy { it.first }
        val mergedRanges = mutableListOf<ULongRange>()
        var currentRange = sortedRanges[0]

        for (i in 1 until sortedRanges.size) {
            val nextRange = sortedRanges[i]
            if (currentRange.last + 1u >= nextRange.first) {
                currentRange = currentRange.first..maxOf(currentRange.last, nextRange.last)
            } else {
                mergedRanges.add(currentRange)
                currentRange = nextRange
            }
        }

        mergedRanges.add(currentRange)

        return mergedRanges
    }

    fun part2(ranges: List<ULongRange>) =
        mergeRanges(ranges)
            .sumOf { it.last - it.first + 1u }

    println("Part 1: ${part1(availableIds, ranges)}")
    println("Part 2: ${part2(ranges)}")
}