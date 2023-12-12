import kotlin.test.assertEquals

fun main() {

    fun allZeros(sequence: List<Long>): Boolean {
        for (n in sequence) {
            if (n != 0L) {
                return false
            }
        }
        return true
    }

    fun predict(sequence: List<Long>, end: Boolean): Long {

        if (allZeros(sequence)) {
            return 0
        }

        val subsequence = sequence
            .windowed(2, 1)
            .map { it[1] - it[0] }

        val prediction = predict(subsequence, end)
        if (end) {
            return sequence.last() + prediction
        }
        return sequence.first() - prediction
    }

    fun part1(input: List<String>): Long {
        return input.sumOf {
            val history = it
                .split(" ")
                .map(String::toLong)
            predict(history, true)
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf {
            val history = it
                .split(" ")
                .map(String::toLong)
            predict(history, false)
        }
    }


    val testInput = readInput("samples/day9.txt")
    assertEquals(114, part1(testInput))

    val input = readInput("input/day9.txt")
    val solution1 = part1(input)
    assertEquals(1995001648, solution1)
    solution1.println()

    assertEquals(2, part2(testInput))

    val solution2 = part2(input)
    assertEquals(988, solution2)
    solution2.println()

}
