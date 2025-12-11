package year_2025

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.util.BitSet
import kotlin.time.measureTimedValue

class Day10(input: String) {

    data class Machine(val lights: BitSet, val buttons: List<BitSet>, val joltage: List<Int>)

    private fun lights(data: String): BitSet {
        val result = BitSet()
        data.withIndex().filter { it.value == '#' }.forEach { result.set(it.index - 1) }
        return result
    }

    private fun buttons(data: List<String>) =
        data.map {
            val result = BitSet()
            it.trim('(', ')')
                .split(',')
                .map(String::toInt)
                .forEach(result::set)
            result
        }

    private fun joltage(data: String) =
        data.trim('{', '}')
            .split(',')
            .map(String::toInt)

    val factory = input.lines()
        .map { line ->
            val parts = line.split(" ")
            Machine(
                lights(parts.first()),
                buttons(parts.subList(1, parts.size - 1)),
                joltage(parts.last())
            )
        }

    fun findMinPresses(m: Machine): Int {

        data class State(val lights: BitSet, val count: Int, val pastButton: BitSet? = null)

        val states = ArrayDeque<State>()
        states.add(State(BitSet(), 0))

        while (true) {
            val current = states.removeFirst()
            if (current.lights == m.lights)
                return current.count

            for (button in m.buttons) {
                if (button == current.pastButton) continue
                val newLights = BitSet.valueOf(current.lights.toLongArray())
                newLights.xor(button)
                val newState = State(newLights, current.count + 1, button)
                states.add(newState)
            }
        }
    }

    fun solvePart1(): Int =
        factory.sumOf { findMinPresses(it) }

    fun solvePart2(): Int = runBlocking(Dispatchers.IO) {
        factory.mapIndexed { index, machine ->
            async {
                println("Computing $index")
                val (value, duration) = measureTimedValue { findMinPresses2(machine) }
                println("...completed $index with time $duration")
                value
            }
        }
            .awaitAll()
            .sum()
    }

    fun findMinPresses2(m: Machine): Int {
        val init = List(m.joltage.size) { 0 }
        val keys = ArrayDeque<List<Int>>()
        keys.add(init)
        val states = mutableMapOf(init to 0)

        while (true) {
            val current = keys.removeFirst()
            val count = states.remove(current)!!
            if (current == m.joltage) {
                return count
            }
            for (button in m.buttons) {
                val newJoltage = current.toMutableList()
                // button.stream().forEach { newJoltage[it]++ }
                var idx = button.nextSetBit(0)
                while (idx >= 0) {
                    newJoltage[idx]++
                    idx = button.nextSetBit(idx + 1)
                }
                if (newJoltage in states) continue
                if ((0 until m.joltage.size).any { newJoltage[it] > m.joltage[it] }) continue
                keys.add(newJoltage)
                states[newJoltage] = count + 1
            }
        }
    }
}