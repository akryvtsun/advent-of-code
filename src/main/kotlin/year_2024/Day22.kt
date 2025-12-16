package year_2024

class Day22(val input: String) {

    companion object {
        fun nextSecret(num: Long): Long {
            var secret = doIt(num) { it shl 6 }
            secret = doIt(secret) { it shr 5 }
            return doIt(secret) { it shl 11 }
        }

        private inline fun doIt(num: Long, op: (Long) -> Long) : Long =
            prune(op(num) xor num)

        private inline fun prune(num: Long) = num and 0xFFFFFF
    }

    fun transform(input: String): List<Long> {
        return input.lines().map(String::toLong)
    }

    private fun nextSecret2000(num: Long): Long {
        var secret = num
        for (i in 1 .. 2000) {
            secret = nextSecret(secret)
        }
        return secret
    }

    val buyers = transform(input)

    fun solvePart1(): Long {
        return buyers.sumOf { nextSecret2000(it) }
    }

    data class Seq(val d1: Long, val d2: Long, val d3: Long, val d4: Long)

    private fun secrets2000(num: Long) = sequence {
        var secret = num
        yield(secret)
        for (i in 1 .. 2000) {
            secret = nextSecret(secret)
            yield(secret)
        }
    }

    private fun createBuyerModel(secret: Long): Map<Seq, Long> {
        val model = mutableMapOf<Seq, Long>()
        val inter = secrets2000(secret)
            .map { it % 10 }
            .zipWithNext()
            .map { it.second - it.first to it.second }
        inter.windowed(4, 1)
            .map { (d1, d2, d3, d4) -> Seq(d1.first, d2.first, d3.first, d4.first) to d4.second }
            .forEach { if (model[it.first] == null) model[it.first] = it.second }
        return model
    }

    private fun seqBananasValue(model: List<Map<Seq, Long>>, seq: Seq): Long {
        return model.sumOf { it[seq] ?: 0 }
    }

    fun solvePart2(): Long {
        val model = buyers.map { createBuyerModel(it) }
        val allSeqs = model
            .flatMap { it.keys }
            .toSet()
        return allSeqs
            .maxOf { seq -> seqBananasValue(model, seq) }
    }
}