import util.R

fun main() {

    val input = R.getContents("day06/input.txt")


    fun part1(input: List<String>): Long {

        val numbersList = input.dropLast(1)
            .map {
                it.split(" ")
            }

        val opsList = input.last()
            .split("")
            .filter { it.isNotBlank() }

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

    fun part2(input: List<String>): Long {

        val numbersList = input.dropLast(1)

        val opsList = input.last()
            .split("")
            .filter { it.isNotBlank() }

        // Arrange numbers by their position
        val maxLength = numbersList.maxOfOrNull { it.length } ?: 0
        val arrangedNumsList = (0..maxLength)
            .map {
                numbersList.map { str -> str.getOrNull(it) ?: ' ' }
                    .joinToString("")
                    .trim()
            }

        // Split into groups, considering blanks as separators
        val groupsList = mutableListOf<List<Long>>()
        var tempList = mutableListOf<Long>()

        for (i in arrangedNumsList) {
            val n = i.toLongOrNull()
            if (n != null) {
                tempList.add(n)
            } else {
                groupsList.add(tempList)
                tempList = mutableListOf()
            }
        }

        return groupsList.mapIndexed { rowIndex, row ->

            val result = when (val op = opsList[rowIndex]) {
                "+" -> row.sum()
                "*" -> row.reduce { acc, num -> acc * num }
                else -> throw IllegalArgumentException("Unknown operation: $op")
            }
            result

        }.sum()
    }



    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}