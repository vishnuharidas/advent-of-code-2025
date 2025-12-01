import util.R

// Day 01: https://adventofcode.com/2025/day/1
fun main() {

    val input = R.getContents("day01/input.txt")
        .filter(String::isNotEmpty)
        .map { it.first() to it.takeLast(it.count() - 1).toInt() } // <DIRECTION><DISTANCE>

    var position = 50

    var zerosCount = 0

    // Problem 01:
    for ((direction, distance) in input) {
        when (direction) {
            'R' -> position += distance
            'L' -> position -= distance
        }

        if (position % 100 == 0) {
            zerosCount++
        }
    }

    println("Solution 01: $zerosCount")


    // Problem 02:
    position = 50
    zerosCount = 0

    for ((direction, distance) in input) {

        val delta = if (direction == 'R') 1 else -1

        // Couldn't find a better implementation than just iterating step by step :/
        repeat(distance) {
            position += delta
            if (position % 100 == 0) {
                zerosCount++
            }
        }

    }

    println("Solution 02: $zerosCount")

}