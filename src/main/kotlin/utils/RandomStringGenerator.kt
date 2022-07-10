package utils

import java.util.*
import java.util.stream.IntStream

class RandomStringGenerator {
    companion object {
        fun getRandCharSequence(length: Int): String {
            val builder = StringBuilder()
            IntStream.range(0, length).forEach { _: Int ->
                val symbol = (97 + Random().nextInt(26)).toChar()
                builder.append(symbol)
            }
            return builder.toString()
        }
    }
}