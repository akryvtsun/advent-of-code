package day_14

class Task2 {

    companion object {

        private fun roundMove(value: Int, delta: Int, border: Int): Int {
            val newValue = (value + delta) % border
            return if (newValue < 0) border + newValue else newValue
        }

        fun solve(height: Int, width: Int, robots: List<Robot>): Long {

            fun Robot.doStep(): Robot {
                var newY = roundMove(this.coord.y, this.delta.y, height)
                var newX = roundMove(this.coord.x, this.delta.x, width)
                return Robot(Point(newY, newX), this.delta)
            }

            fun printTree(step: Int, robots: List<Robot>) {
                println(">>>>>>>>>>>>>>>>> STEP: $step >>>>>>>>>>>>>>>>>")
                for (y in 0..height) {
                    for (x in 0..width) {
                        if (robots.any { it.coord == Point(y, x) }) {
                            print("#")
                        } else {
                            print(" ")
                        }
                    }
                    println()
                }
            }

            var curRobots = robots
            repeat(100) {
                printTree(it, curRobots)
                curRobots = curRobots.map { it.doStep() }
            }
            return 0
        }
    }
}