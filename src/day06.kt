import util.R

fun main() {

    val input = R.getContents("day06/input.txt")

    val numbersList = input.dropLast(1)
        .map {
            it.split(" ")
        }

    val opsList = input.last()
        .split("")
        .filter { it.isNotBlank() }

    fun part1(numbersList: List<List<String>>, opsList: List<String>): Long {

        // Convert strings to longs, ignoring blank strings
        val numbers = numbersList.map { row ->
            row.filter { numStr -> numStr.isNotBlank() }
                .map { numStr -> numStr.toLong() }
        }

        // Transpose to collect numbers by their position
        val transposedNumbers = numbers.first()
            .indices
            .map {
                numbers.map { row -> row[it] }
            }

        val resultsList = transposedNumbers.mapIndexed { rowIndex, row ->

            val result = when (val op = opsList[rowIndex]) {
                "+" -> row.sum()
                "*" -> row.reduce { acc, num -> acc * num }
                else -> throw IllegalArgumentException("Unknown operation: $op")
            }
            result
        }

        return resultsList.sum()
    }


    println("Part 1: ${part1(numbersList, opsList)}")
}