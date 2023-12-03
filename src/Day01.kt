import kotlin.test.assertEquals

val symbols = arrayOf(
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
)

fun main() {

    fun part1(input: List<String>): Int {

        var calibrationSum: Int = 0

        for (line in input) {

            val chars = line.toCharArray()

            for (c in chars) {
                if (c.isDigit()) {
                    calibrationSum += (c.digitToInt() * 10)
                    break
                }
            }

            for (c in chars.reversed()) {
                if (c.isDigit()) {
                    calibrationSum += c.digitToInt()
                    break
                }
            }
        }

        return calibrationSum
    }

    fun part2(input: List<String>): Int {

        var calibrationSum: Int = 0

        for (line in input) {

            var firstIndex = line.length
            var firstDigit = 0
            var lastIndex = -1
            var lastDigit = 0

            symbols.forEachIndexed { index, symbol ->

                val symbolFirst = line.indexOf(symbol)
                val symbolLast = line.lastIndexOf(symbol)

                if (symbolFirst >= 0) {
                    if (symbolFirst < firstIndex) {
                        firstIndex = symbolFirst
                        firstDigit = index % 10
                    }

                    if (symbolLast > lastIndex) {
                        lastIndex = symbolLast
                        lastDigit = index % 10
                    }
                }

            }
            calibrationSum += (firstDigit * 10) + lastDigit

        }
        return calibrationSum
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("samples/day1.1.txt")
    assertEquals(142, part1(testInput1))

    val input = readInput("input/day1.txt")
    val solution1 = part1(input)
    assertEquals(54331, solution1)
    solution1.println()

    val testInput2 = readInput("samples/day1.2.txt")
    assertEquals(281, part2(testInput2))

    val solution2 = part2(input)
    assertEquals(54518,solution2)
    solution2.println()


}
