package day_15

class Task1 {

    companion object {

        fun solve(data: Warehouse, commands: List<Command>): Int {
            val boxes = emptyList<Point>()
            return boxes
                .sumOf { it.y * 100 + it.x }
        }
    }
}