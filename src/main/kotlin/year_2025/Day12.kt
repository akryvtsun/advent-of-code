package year_2025

class Day12(input: String) {

    data class Figure(val pattern: List<String>)
    data class Area(val wide: Int, val height: Int, val set: List<Int>)

    private val data = input.split("\n\n")
    val figures = getFigures(data.dropLast(1))
    val areas = getAreas(data.last())

    fun getFigures(data: List<String>) =
        data.map {
            Figure(it.split("\n").drop(1))
        }

    fun getAreas(data: String) =
        data.split("\n").map {
            val (wide, long) = it.substringBefore(": ").split('x').map(String::toInt)
            val set = it.substringAfter(": ").split(' ').map(String::toInt)
            Area(wide, long, set)
        }


    fun Area.canBeFilled(): Boolean =
        this.wide * this.height >= this.set.mapIndexed { idx, count ->
            count * figures[idx].pattern.sumOf { it.count { it == '#' } }
        }.sum()

    fun solvePart1(): Int {
        return areas.count {
            println("area: $it")
            val result = it.canBeFilled()
            println("... can be filled = $result")
            result
        }
    }
}
