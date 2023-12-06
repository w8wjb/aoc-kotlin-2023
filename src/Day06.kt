import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.test.assertEquals

fun main() {

    data class Race(val time: Long, val distance: Long) {}

    fun part1(input: List<Race>): Long {


        return input.fold(1L) { mul, race ->
            val b = race.time.toDouble()
            val c = race.distance.toDouble()

            val x1 = (b - sqrt(b.pow(2) - (4 * c))) / 2
            val x2 = (b + sqrt(b.pow(2) - (4 * c))) / 2
            val span = ceil(x2) - floor(x1) - 1

            mul * span.toLong()
        }

    }


    // Time:      7  15   30
    // Distance:  9  40  200
    val testInput1 = listOf(
        Race(7, 9),
        Race(15, 40),
        Race(30, 200),
    )

    val testInput2 = listOf(
        Race(71530, 940200),
    )

    // Time:        40     81     77     72
    // Distance:   219   1012   1365   1089
    val puzzleInput1 = listOf(
        Race(40, 219),
        Race(81, 1012),
        Race(77, 1365),
        Race(72, 1089)
    )

    val puzzleInput2 = listOf(
        Race(40817772, 219101213651089)
    )

    assertEquals(288, part1(testInput1))

    val solution1 = part1(puzzleInput1)
    assertEquals(861300, solution1)
    solution1.println()

    assertEquals(71503, part1(testInput2))

    val solution2 = part1(puzzleInput2)
    assertEquals(28101347, solution2)
    solution2.println()


}
