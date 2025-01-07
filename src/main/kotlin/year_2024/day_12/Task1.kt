package year_2024.day_12

class Task1 {

    companion object {

        fun perimeter(r: Region): Long {
            return r.fields
                .flatMap { p -> Direction.entries.map { p + it.delta } }
                .count { it !in r}
                .toLong()
        }

        fun solve(map: List<List<Char>>): Long {
            return findRegions(map).sumOf { it.area() * perimeter(it) }
        }
    }
}