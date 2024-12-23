package day_23

fun transform(input: String): List<Pair<String, String>> {
    return input.lines()
        .map { link ->
            val (left, right) = link.split("-")
            left to right
        }
}






