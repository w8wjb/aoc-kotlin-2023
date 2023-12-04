import kotlin.math.min
import kotlin.math.pow
import kotlin.test.assertEquals

fun main() {

    data class Card(val line: String) {
        val winningNumbers = mutableSetOf<String>()
        val scratched = mutableSetOf<String>()
        val matches: Int
        var copies = 1

        init {
            val (_, winningSection, scratchedSection) = line.split(":", "|").map { it.trim() }
            winningNumbers.addAll(winningSection.split("\\s+".toRegex()))
            scratched.addAll(scratchedSection.split("\\s+".toRegex()))
            matches = (winningNumbers intersect scratched).size
        }

    }


    fun part1(input: List<String>): Int {

        val deck = input.map(::Card)
        return deck
            .filter { it.matches > 0 }
            .sumOf { 2.0.pow(it.matches - 1).toInt() }

    }

    fun part2(input: List<String>): Int {

        val deck = input.map(::Card)
        deck.forEachIndexed { c, card ->
            val start = c + 1
            val end = min(start + card.matches, deck.size)
            for (i in start..<end) {
                deck[i].copies += card.copies
            }
        }

        return deck.sumOf { it.copies }
    }

    val testInput = readInput("samples/day4.txt")
    assertEquals(13, part1(testInput))

    val input = readInput("input/day4.txt")
    val solution1 = part1(input)
    assertEquals(23750, solution1)
    solution1.println()

    assertEquals(30, part2(testInput))

    val solution2 = part2(input)
    assertEquals(13261850, solution2)
    solution2.println()


}
