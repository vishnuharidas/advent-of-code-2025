import util.R

class Matrix<E>(input: List<List<E>>) {

    private val data = input.map { it.toMutableList() }.toMutableList() // Made it mutable for debugging purposes
    val rows: Int = input.size
    val cols: Int = if (rows > 0) input[0].size else 0

    operator fun get(r: Int, c: Int): E = data[r][c]
    operator fun set(r: Int, c: Int, value: E) {
        data[r][c] = value
    }

    fun north(r: Int, c: Int, default: E): E = if (r > 0) data[r - 1][c] else default
    fun south(r: Int, c: Int, default: E): E = if (r < rows - 1) data[r + 1][c] else default
    fun west(r: Int, c: Int, default: E): E = if (c > 0) data[r][c - 1] else default
    fun east(r: Int, c: Int, default: E): E = if (c < cols - 1) data[r][c + 1] else default
    fun northEast(r: Int, c: Int, default: E): E = if (r > 0 && c < cols - 1) data[r - 1][c + 1] else default
    fun northWest(r: Int, c: Int, default: E): E = if (r > 0 && c > 0) data[r - 1][c - 1] else default
    fun southEast(r: Int, c: Int, default: E): E = if (r < rows - 1 && c < cols - 1) data[r + 1][c + 1] else default
    fun southWest(r: Int, c: Int, default: E): E = if (r < rows - 1 && c > 0) data[r + 1][c - 1] else default

    fun neighbors(r: Int, c: Int, default: E): List<E> {
        return listOf(
            north(r, c, default),
            south(r, c, default),
            west(r, c, default),
            east(r, c, default),
            northEast(r, c, default),
            northWest(r, c, default),
            southEast(r, c, default),
            southWest(r, c, default)
        )
    }

    fun forEach(action: (row: Int, col: Int, item: E) -> Unit) {
        data.forEachIndexed { r, rowList ->
            rowList.forEachIndexed { c, item ->
                action(r, c, item)
            }
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        data.forEach { row ->
            sb.append(row.joinToString(" ")).append("\n")
        }
        return sb.toString()
    }
}

fun main() {

    val input = R.getContents("day04/input.txt")

    fun part1(input: List<List<String>>): Int {

        val matrix = Matrix(input)

        var movables = 0

        // Just for debugging purposes
        val matrixMovables = Matrix(input)

        matrix.forEach { row, col, item ->

            if (item == "@") {
                val neighbors = matrix.neighbors(row, col, ".").count { it == "@" }

                if (neighbors < 4) {
                    matrixMovables[row, col] = "x"
                    movables++
                } else {
                    matrixMovables[row, col] = "@"
                }
            } else {
                matrixMovables[row, col] = "."
            }

        }

        //println(matrix)
        //println(matrixMovables)

        return movables
    }


    val inputCleaned = input.map { it.split("").filter { c -> c.isNotEmpty() } }
    println("Part 1: Movable rolls = ${part1(inputCleaned)}")

}