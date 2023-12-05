import kotlin.test.assertEquals

fun main() {

    data class RangeShift(val destStart: Long, val srcStart: Long, val rangeLen: Long) {
        val src = LongRange(srcStart, srcStart + rangeLen - 1)
        val offset = destStart - srcStart
    }

    val seeds: MutableList<Long> = mutableListOf()
    val steps = mutableListOf<List<RangeShift>>()

    fun parse(input: List<String>) {

        seeds.clear()
        steps.clear()

        var currentShifts = mutableListOf<RangeShift>()

        for (line in input) {

            if (line.isBlank()) {
                continue
            }

            if (line.startsWith("seeds: ")) {
                seeds.addAll(
                    line.removePrefix("seeds: ")
                        .split(" ")
                        .map(String::toLong)
                )


            } else if (line[0].isDigit()) {
                val (destStart, srcStart, rangeLen) = line.split(" ").map(String::toLong)
                currentShifts.add(RangeShift(destStart, srcStart, rangeLen))

            } else {
                currentShifts = mutableListOf()
                steps.add(currentShifts)
            }

        }

    }

    fun part1(input: List<String>): Long {

        parse(input)

        for (step in steps) {

            val tmpSeeds = seeds.toList()
            for (i in tmpSeeds.indices) {
                val seed = tmpSeeds[i]
                for (shift in step) {
                    if (shift.src.contains(seed)) {
                        seeds[i] = seed + shift.offset
                        break
                    }
                }
            }
        }

        return seeds.min()

    }

    fun part2(input: List<String>): Long {

        parse(input)

        val seedRanges = mutableListOf<LongRange>()

        for (i in 0..<seeds.size step 2) {
            val start = seeds[i]
            val len = seeds[i + 1]
            val end = start + len - 1 // inclusive
            seedRanges.add(LongRange(start, end))
        }

        for (step in steps) {

            var i = 0
            while (i < seedRanges.size) {

                var seedRange = seedRanges[i]

                for (shift in step) {

                    if (shift.src.contains(seedRange.first)) {

                        if (seedRange.last > shift.src.last) {
                            val smallerRange = LongRange(seedRange.first, shift.src.last)
                            val remainderRange = LongRange(smallerRange.last + 1, seedRange.last)
                            seedRanges.add(remainderRange)
                            seedRange = smallerRange
                        }

                        seedRanges[i] = LongRange(
                            seedRange.first + shift.offset,
                            seedRange.last + shift.offset
                        )
                        break
                    }
                }

                i++
            }
        }

        return seedRanges.minOf { it.first }
    }

    val testInput = readInput("samples/day5.txt")
    assertEquals(35, part1(testInput))

    val input = readInput("input/day5.txt")
    val solution1 = part1(input)
    assertEquals(309796150, solution1)
    solution1.println()

    assertEquals(46, part2(testInput))

    val solution2 = part2(input)
    assertEquals(50716416, solution2)
    solution2.println()


}
