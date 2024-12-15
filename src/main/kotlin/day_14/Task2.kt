package day_14

class Task2 {

    companion object {

        private fun roundMove(value: Int, delta: Int, border: Int): Int {
            val newValue = (value + delta) % border
            return if (newValue < 0) border + newValue else newValue
        }

        fun solve(height: Int, width: Int, robots: List<Robot>): Int {

            fun Robot.doStep(): Robot {
                var newY = roundMove(this.coord.y, this.delta.y, height)
                var newX = roundMove(this.coord.x, this.delta.x, width)
                return Robot(Point(newY, newX), this.delta)
            }

            fun checkTree(robots: List<Robot>): Boolean {
                for (y in 0..height) {
                    var line = StringBuilder()
                    for (x in 0..width) {
                        val char = if (robots.any { it.coord == Point(y, x) }) '#' else ' '
                        line.append(char)
                    }
                    val found = line.indexOf("########") != -1
                    if (found) return true
                }
                return false
            }

            val treeState = generateSequence(0 to robots) { state ->
                state.first + 1 to state.second.map { it.doStep() }
            }
                .first { checkTree(it.second) }
            println(">>>>>>>>>>>>>>>>> STEP: ${treeState.first} >>>>>>>>>>>>>>>>>")
            for (y in 0..height) {
                var line = StringBuilder()
                for (x in 0..width) {
                    val char = if (treeState.second.any { it.coord == Point(y, x) }) '#' else ' '
                    line.append(char)
                }
                println(line)
            }
            return treeState.first
        }
    }
}