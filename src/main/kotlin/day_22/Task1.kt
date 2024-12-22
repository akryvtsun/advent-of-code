package day_22

data class Node(var isTerminal: Boolean = false, var next: MutableMap<Char, Node>? = null)

class Task1 {

    companion object {

        fun nextSecret(num: Long): Long {
            var secret = doIt(num) { it shl 6 }
            secret = doIt(secret) { it shr 5 }
            return doIt(secret) { it shl 11 }
        }

        private fun doIt(num: Long, op: (Long) -> Long) : Long =
            prune(op(num) xor num)

        private fun prune(num: Long) =
            num % 16777216

        fun solve(buyers: List<Long>): Long {
            return buyers
                .map { nextSecret2000(it) }
                //.also { println(it) }
                .sum()
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