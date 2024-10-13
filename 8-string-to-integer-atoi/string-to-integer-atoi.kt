class Solution {
    fun myAtoi(s: String): Int {
        val sBuilder = StringBuilder()
        val iterator = s.asIterable().iterator()
        var builder: Builder = Stage1
        while (iterator.hasNext()) {
            val next = iterator.next()
            builder = builder.build(next, sBuilder) ?: break
        }
        println(sBuilder.toString())
        return try {
            Integer.parseInt(sBuilder.toString())
        } catch (_: Exception) {
            val length = sBuilder.length
            return when {
                length == 0           -> 0
                length == 1           -> if (sBuilder[0].isDigit()) Int.MAX_VALUE else 0
                sBuilder[0].isDigit() -> Int.MAX_VALUE
                else                  -> Int.MIN_VALUE
            }
        }
    }
}

interface Builder {
    fun build(c: Char, builder: StringBuilder): Builder?
}

object Stage1:Builder {

    override fun build(c: Char, builder: StringBuilder): Builder? {
        return when {
            c == ' '             -> this
            c == '+' || c == '-' -> Stage2.build(c, builder)
            c.isDigit()          -> Stage3.build(c, builder)
            else                 -> null
        }
    }
}

object Stage2:Builder {
    override fun build(c: Char, builder: StringBuilder): Builder? {
        when {
            c == '-'    -> {
                builder.append(c)
                return Stage3
            }
            c.isDigit() -> return Stage3.build(c, builder)
            c == '+'    -> return Stage3
            else        -> return null
        }
    }
}

object Stage3 : Builder {
    override fun build(c: Char, builder: StringBuilder): Builder? {
        when {
            c.isDigit() -> {
                builder.append(c)
                return Stage3
            }
            else        -> return null
        }
    }
}