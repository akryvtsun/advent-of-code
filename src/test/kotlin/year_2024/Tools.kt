package year_2024

import java.io.File

fun taskData(year: Int, task: Int) =
    File("src/test/resources/year_$year/day_${String.format("%02d", task)}/TaskData.txt")
