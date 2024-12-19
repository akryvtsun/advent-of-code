package day_19

fun transform(input: String): Pair<List<String>, List<String>> {
    val (patterns, designs) = input.split("\n\n")
    return patterns.split(", ") to designs.lines()
}






