package year_2024.day_22

class Task2 {

    data class Seq(val d1: Long, val d2: Long, val d3: Long, val d4: Long)

    companion object {

        fun nextSecret(num: Long): Long {
            var secret = doIt(num) { it shl 6 }
            secret = doIt(secret) { it shr 5 }
            return doIt(secret) { it shl 11 }
        }

        private inline fun doIt(num: Long, op: (Long) -> Long) : Long =
            prune(op(num) xor num)

        private inline fun prune(num: Long) = num and 0xFFFFFF

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

        fun solve(buyers: List<Long>): Long {
            val model = buyers.map { createBuyerModel(it) }
            val allSeqs = model
                .flatMap { it.keys }
                .toSet()
            return allSeqs
                .maxOf { seq -> seqBananasValue(model, seq) }
        }
    }
}