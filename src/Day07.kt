import kotlin.test.assertEquals

fun main() {

    data class Hand(val line: String, val jokersWild: Boolean = false) {
        val cards: String
        val bid: Int
        val handType: Int

        init {
            val parts = line.split(" ")
            this.cards = parts[0]
            this.bid = parts[1].toInt()

            val charCount = mutableMapOf(' ' to 0)

            for (c in this.cards) {
                charCount[c] = charCount.getOrDefault(c, 0) + 1
            }

            var jokers = 0
            if (jokersWild) {
                jokers = charCount['J'] ?: 0
                charCount['J'] = 0
            }

            val freqs = charCount.values.sortedDescending()
            val primaryCount = freqs[0] + jokers
            val secondaryCount = freqs[1]

            this.handType = when (primaryCount) {
                5 -> 6
                4 -> 5
                3 -> if (secondaryCount == 2) 4 else 3
                2 -> if (secondaryCount == 2) 2 else 1
                else -> 0
            }

        }

    }

    class CamelCardComparator(val cardOrder: String) : Comparator<Hand> {
        override fun compare(a: Hand?, b: Hand?): Int {
            if (a == null || b == null) {
                return 0
            }

            if (a.handType == b.handType) {
                for ((ac, bc) in a.cards.zip(b.cards)) {
                    if (ac == bc) {
                        continue
                    }
                    return cardOrder.indexOf(ac) - cardOrder.indexOf(bc)
                }
            }
            return a.handType.compareTo(b.handType)
        }

    }


    fun part1(input: List<String>): Int {
        return input
            .map(::Hand)
            .sortedWith(CamelCardComparator( "23456789TJQKA"))
            .foldIndexed(0) { i, sum, hand ->
                sum + (hand.bid * (i + 1))
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { Hand(it, true) }
            .sortedWith(CamelCardComparator("J23456789TQKA"))
            .foldIndexed(0) { i, sum, hand ->
                sum + (hand.bid * (i + 1))
            }
    }

    val testInput = readInput("samples/day7.txt")
    assertEquals(6440, part1(testInput))

    val input = readInput("input/day7.txt")
    val solution1 = part1(input)
    assertEquals(251545216, solution1)
    solution1.println()

    assertEquals(5905, part2(testInput))

    val solution2 = part2(input)
    assertEquals(250384185, solution2)
    solution2.println()

}
