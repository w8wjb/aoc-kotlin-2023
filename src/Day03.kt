import kotlin.test.assertEquals

fun main() {

    data class Point(val x: Int, val y: Int) {
        fun neighbors(): Set<Point> {
            val neighbors = mutableSetOf<Point>()
            for (nx in (this.x - 1)..(this.x + 1)) {
                for (ny in (this.y - 1)..(this.y + 1)) {
                    neighbors.add(Point(nx, ny))
                }
            }
            return neighbors
        }
    }

    class Grid(input: List<String>) {
        val parts = mutableListOf<Pair<Int, Set<Point>>>()
        val gears = mutableSetOf<Point>()
        val symbols = mutableSetOf<Point>()

        init {
            for ((y, line) in input.withIndex()) {

                var currentNum = 0
                var adjacencies = mutableSetOf<Point>()

                for ((x, c) in line.withIndex()) {

                    val p = Point(x, y)

                    if (c.isDigit()) {
                        adjacencies.addAll(p.neighbors())
                        currentNum = (currentNum * 10) + c.digitToInt()

                    } else {
                        if (currentNum > 0) {
                            parts.add(Pair(currentNum, adjacencies))
                            currentNum = 0
                            adjacencies = mutableSetOf()
                        }

                        if (c == '*') {
                            gears.add(p)
                        }

                        if (c != '.') {
                            symbols.add(p)
                        }
                    }
                }

                if (currentNum > 0) {
                    parts.add(Pair(currentNum, adjacencies))
                }

            }
        }
    }

    fun part1(grid: Grid): Int {
        return grid.parts
            .filter { (_, adjacencies) ->
                (adjacencies intersect grid.symbols).isNotEmpty()
            }
            .sumOf { it.first }
    }

    fun part2(grid: Grid): Int {
        
        var sumGearRatio = 0

        for (gear in grid.gears) {
            var ratio = 1
            var count = 0
            for ((partNum, adjacencies) in grid.parts) {

                if (gear in adjacencies) {
                    count++
                    if (count > 2) {
                        break
                    }
                    ratio *= partNum
                }
            }

            if (count == 2) {
                sumGearRatio += ratio
            }
        }

        return sumGearRatio
    }

    val testInput = readInput("samples/day3.txt")
    val testGrid = Grid(testInput)
    assertEquals(4361, part1(testGrid))

    val input = readInput("input/day3.txt")
    val grid = Grid(input)
    val solution1 = part1(grid)
    assertEquals(539637, solution1)
    solution1.println()

    assertEquals(467835, part2(testGrid))

    val solution2 = part2(grid)
    assertEquals(82818007, solution2)
    solution2.println()


}
