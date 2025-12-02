package year_2025

class Day02(private val input: String) {

    fun solvePart1(): Long {
        return input.split(",")
            .flatMap {
                val (from, to) = it.split("-")
                //println("from: $from, to: $to")
                val res = mutableListOf<Long>()
                for (i in (from.toLong())..(to.toLong())) {
                    val s = i.toString()
                    if (s.length % 2 == 0) {
                        val popolam = s.length / 2
                        //println(popolam)
                        val first = s.substring(0, popolam).toLong()
                        val second = s.drop(popolam).toLong()
                        if (first == second) {
                            res.add(i)
                        }
                    }
                }
                res
            }.sum()
    }

    fun solvePart2(): Long {
        fun isInvalid(n: Long): Boolean {
            val s = n.toString()
            for (i in 2..(s.length)) {
                if (s.length % i == 0) {
                    val len = s.length / i
                    val set = mutableSetOf<String>()
                    set.addAll(s.chunked(len))
                    if (set.size == 1) return true
                }
            }
            return false
        }

        return input.split(",")
            .flatMap {
                val (from, to) = it.split("-")
                val res = mutableListOf<Long>()
                for (i in (from.toLong())..(to.toLong())) {
                    if (isInvalid(i)) {
                        res.add(i)
                    }
                }
                res
            }.sum()
    }
}

