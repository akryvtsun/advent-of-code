package year_2024.day_22

class Task1 {

    companion object {

        fun nextSecret(num: Long): Long {
            var secret = doIt(num) { it shl 6 }
            secret = doIt(secret) { it shr 5 }
            return doIt(secret) { it shl 11 }
        }

        private inline fun doIt(num: Long, op: (Long) -> Long) : Long =
            prune(op(num) xor num)

        private inline fun prune(num: Long) = num and 0xFFFFFF

        fun solve(buyers: List<Long>): Long {
            return buyers.sumOf { nextSecret2000(it) }
        }

        private fun nextSecret2000(num: Long): Long {
            var secret = num
            for (i in 1 .. 2000) {
                secret = nextSecret(secret)
            }
            return secret
        }
    }
}