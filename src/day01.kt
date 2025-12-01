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
}