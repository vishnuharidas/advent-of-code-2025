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

    fun part2(input: List<String>): Long {

        fun countFinishes(
            grid: List<String>,
            row: Int,
            col: Int,
            visited: MutableMap<Pair<Int, Int>, Long>
        ): Long {
            // reached past bottom -> one successful finish
            if (row >= grid.size) {
                return 1L
            }

            // if already visited, return cached result
            if (visited[Pair(row, col)] != null) {
                return visited[Pair(row, col)]!!
            }

            val result = when (grid[row][col]) {
                '^' -> {
                    // go left and right from this row/col
                    countFinishes(grid, row, col - 1, visited) + countFinishes(grid, row, col + 1, visited)
                }

                '.', 'S' -> {
                    // go straight down
                    countFinishes(grid, row + 1, col, visited)
                }

                else -> 0L
            }

            // cache result - to avoid recomputation every time a ray passes through this point again in the future.
            visited[Pair(row, col)] = result

            return result
        }


        val firstPosition = input.first().indexOf("S")
        val visited = mutableMapOf<Pair<Int, Int>, Long>()

        val finishCount = countFinishes(input, 0, firstPosition, visited)

        return finishCount
    }

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")

}