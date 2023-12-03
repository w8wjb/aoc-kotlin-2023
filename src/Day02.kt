import java.util.*
import kotlin.math.max
import kotlin.test.assertEquals

fun main() {

    data class Game(val id: Int) {
        var maxRed = 0
        var maxGreen = 0
        var maxBlue = 0
    }

    fun parseLine(line: String): Game {
        val scanner = Scanner(line).useDelimiter("[\\s:;,]+")

        scanner.next() // Eat the "Game suffix
        val game = Game(scanner.nextInt())

        while (scanner.hasNextInt()) {
            val cubes = scanner.nextInt()
            when (scanner.next()) {
                "red" -> game.maxRed = max(game.maxRed, cubes)
                "green" -> game.maxGreen = max(game.maxGreen, cubes)
                "blue" -> game.maxBlue = max(game.maxBlue, cubes)
            }
        }
        return game
    }


    fun part1(input: List<String>): Int {
        return input
            .map(::parseLine)
            .filter { it.maxRed <= 12 && it.maxGreen <= 13 && it.maxBlue <= 14 }
            .sumOf { it.id }

    }

    fun part2(input: List<String>): Int {
        return input
            .map(::parseLine)
            .sumOf { it.maxRed * it.maxGreen * it.maxBlue  }
    }

    val testInput = readInput("samples/day2.txt")
    assertEquals(8, part1(testInput))

    val input = readInput("input/day2.txt")
    val solution1 = part1(input)
    assertEquals(3099, solution1)
    solution1.println()

    assertEquals(2286, part2(testInput))

    val solution2 = part2(input)
    assertEquals(72970, solution2)
    solution2.println()


}
