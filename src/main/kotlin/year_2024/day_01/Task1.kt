package year_2024.day_01

import kotlin.math.abs


class Task1 {
    companion object {

        fun solve(list1: List<Long>, list2: List<Long>): Long {
            val sorted1 = list1.sorted()
            val sorted2 = list2.sorted()
            val pairs = sorted1 zip sorted2
            return pairs.map { abs(it.second - it.first) }.sum()
        }
    }
}