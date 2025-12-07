import util.R

fun main() {

    val input = R.getContents("day07/input.txt")

    fun part1(input: List<String>): Long {


        fun countSplits(currentLine: String, remainingLines: List<String>): Long {

            if (remainingLines.isEmpty()) return 0

            val nextLineUpdated = remainingLines.first().toMutableList()

            var splitCount = 0L
            nextLineUpdated.forEachIndexed { index, char ->
                if (currentLine[index] == '|' && char == '^') {
                    // Split the ray
                    nextLineUpdated[index - 1] = '|'
                    nextLineUpdated[index + 1] = '|'

                    splitCount++
                } else if (currentLine[index] == '|' && char == '.') {
                    // Continue the ray
                    nextLineUpdated[index] = '|'
                }
            }


            println(nextLineUpdated.joinToString(""))

            return splitCount + countSplits(nextLineUpdated.joinToString(""), remainingLines.drop(1))
        }

        val firstLine = input.first().replace("S", "|")
        val remainingLines = input.drop(1)

        val splitCount = countSplits(firstLine, remainingLines)

        return splitCount
    }

    println("Part 1: ${part1(input)}")

}