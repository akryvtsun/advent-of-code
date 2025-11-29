import java.io.File

class TaskData(
    private val year: Int,
    private val task: Int,
) {
    fun asString() = getFile().readText()
    fun asLines() = getFile().readLines()

    private fun getFile() =
        File("src/test/resources/year_$year/day_${String.format("%02d", task)}_data.txt")
}