package day_09

class Task2 {

    sealed class FsBlock(open val size: Int)
    data class File(val id: Int, override val size: Int): FsBlock(size)
    data class Space(override val size: Int): FsBlock(size)

    companion object {

        fun solve(input: String): Long {
            val viz = makeViz(input)
            println(viz)
            val packRez = pack(viz)
            println(packRez)
            return calcSum(packRez)
        }

        fun makeViz(data: String): List<FsBlock> {
            var viz = mutableListOf<FsBlock>()
            var id = 0
            data.forEachIndexed { i, c ->
                val size = c.digitToInt()
                val block = if (isFile(i)) File(id++, size) else Space(size)
                viz += block
            }
            return viz
        }

        private fun isFile(pos: Int): Boolean = pos % 2 == 0

        fun pack(data: List<FsBlock>): List<FsBlock> {
            val buf = data.toMutableList()
            var right = data.size-1
            while (right > 0) {
                while (buf[right] is Space) right--
                val rightFile = buf[right]

                var left = 0
                while (left < right) {
                    while (buf[left] !is Space) left++
                    val leftSpace = buf[left]

                    if (leftSpace.size >= rightFile.size) {
                        buf[left++] = rightFile
                        val newSpaceSize = leftSpace.size - rightFile.size
                        if (newSpaceSize > 0) {
                            buf.putSpace(left, newSpaceSize)
                        }
                        buf.putSpace(right--, rightFile.size)
                        break
                    }
                    left++
                }
            }
            return buf
        }

        fun MutableList<FsBlock>.putSpace(pos: Int, size: Int) {
            if (this[pos] is Space) {
                this[pos] = Space(this[pos].size + size)
            } else {
                if (this[pos-1] is Space) {
                    this[pos-1] = Space(this[pos-1].size + size)
                    this.removeAt()
                }
                this.add(pos, Space(size))
            }
        }

        fun calcSum(data: List<FsBlock>): Long {
            var sum = 0L
            var left = 0
            data.forEach { block ->
                for (i in 0 until block.size) {
                    sum += (left+i) * if (block is File) block.id else 0
                }
                left += block.size
            }
            return sum
        }
    }
}