import java.io.File

val UNKNOWN_VALUE = -1

@Deprecated("Use class TaskData")
fun taskData(year: Int, task: Int) =
    File("src/test/resources/year_$year/day_${String.format("%02d", task)}_data.txt")
