package year_2024.day_01


class Task2 {
    companion object {

        fun solve(list1: List<Long>, list2: List<Long>): Long {
            return list1
                .map { target -> target * list2.count { it == target } }
                .sum()
        }
    }
}