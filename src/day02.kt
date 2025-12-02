import util.R

fun main() {

    val inputRangeList = R.getContents("day02/input.txt").first() // single line input
        .split(",")
        .map { range ->
            range.split("-")
                .let { LongRange(it[0].toLong(), it[1].toLong()) }
        }


    // Part 1: find all invalid IDs that are made up by a pattern repeated twice
    val part01InvalidSum = inputRangeList
        .map { range ->

            // Filter IDs that are made up by a pattern repeated twice in each range
            range.filter {
                val idStr = "$it"
                val size = idStr.length
                size % 2 == 0 && idStr.take(size / 2) == idStr.takeLast(size / 2)
            }
        }
        .flatten()
        .sum()


    // Part 2: find all invalid IDs that are made up by a pattern repeated any number of times
    fun anythingRepeated(numberStr: String): Boolean {

        for (i in 1..numberStr.length / 2) {
            val subStr = numberStr.take(i)
            if (subStr.repeat(numberStr.length / i) == numberStr) {
                return true
            }
        }

        return false
    }

    val part02InvalidSum = inputRangeList
        .map { range ->

            // Filter IDs that are made up by **any** pattern repeated twice in each range
            range.filter {
                val idStr = "$it"
                val size = idStr.length
                (size % 2 == 0 && idStr.take(size / 2) == idStr.takeLast(size / 2)) // First condition from part 1
                        || anythingRepeated(idStr) // New condition for part 2
            }
        }
        .also { println(it) }
        .flatten()
        .sum()

    println("Problem 1: Sum of all invalid IDs is $part01InvalidSum")
    println("Problem 2: Sum of all invalid IDs is $part02InvalidSum")
}