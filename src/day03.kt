import util.R

fun main() {

    val input = R.getContents("day03/input.txt")


    fun part01(input: List<String>): Int {

        fun findHighestJoltage(batteryPack: String): String {

            val highestFirst = batteryPack
                .dropLast(1) // Reserve last character for next highest
                .maxOf { it }

            val remainingPack = batteryPack
                .drop(batteryPack.indexOf(highestFirst) + 1) // Drop everything up to and including the highest first

            val highestNext = remainingPack
                .maxOf { it }

            return "$highestFirst$highestNext"
        }

        return input
            .map { findHighestJoltage(batteryPack = it) }
            .sumOf { it.toInt() }

    }


    println("Part 1: ${part01(input)}")
}