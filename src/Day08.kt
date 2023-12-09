import kotlin.test.assertEquals

fun main() {

    fun part1(input: List<String>): Long {

        val path = input[0]
        val nodes = input
            .drop(2)
            .associate {
                val (nid, left, right) = it.split("\\W+".toRegex())
                nid to Pair(left, right)
            }


        var current = "AAA"
        var steps = 0

        while (current != "ZZZ") {
            val fork = nodes[current] ?: continue
            val choose = path[steps % path.length]
            current = if (choose == 'L') fork.first else fork.second
            steps++
        }

        return steps.toLong()
    }

    fun gcd(a: Long, b: Long): Long {
        var num1 = a
        var num2 = b
        while (num2 != 0L) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    fun lcm(a: Long, b: Long): Long {
        if (a == 0L || b == 0L) {
            return 0
        }
        return ((a * b) / gcd(a, b))
    }

    fun lcm(array: LongArray): Long {
        var lcm: Long = 1
        for (value in array) {
            lcm = lcm(lcm, value)
        }
        return lcm
    }

    fun part2(input: List<String>): Long {
        val path = input[0]
        val nodes = input
            .drop(2)
            .associate {
                val (nid, left, right) = it.split("\\W+".toRegex())
                nid to Pair(left, right)
            }


        val startingPositions = nodes.keys.filter { it.endsWith("A") }

        val allSteps = startingPositions.map {
            var current = it
            var steps = 0
            while (!current.endsWith("Z")) {
                val fork = nodes[current] ?: continue
                val choose = path[steps % path.length]
                current = if (choose == 'L') fork.first else fork.second
                steps++
            }
            return@map steps.toLong()
        }

        return lcm(allSteps.toLongArray())
    }


    val testInput1 = readInput("samples/day8.1.txt")
    assertEquals(6, part1(testInput1))

    val input = readInput("input/day8.txt")
    val solution1 = part1(input)
    assertEquals(18157, solution1)
    solution1.println()

    val testInput2 = readInput("samples/day8.2.txt")
    assertEquals(6, part2(testInput2))

    val solution2 = part2(input)
    assertEquals(14299763833181L, solution2)
    solution2.println()

}
