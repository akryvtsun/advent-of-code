package day_12

//data class Plan (val data:  List<List<Char>>)

fun transform(input: String) = input.lines(). map { it.toList() }