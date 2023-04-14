import java.util.concurrent.atomic.AtomicLong
import kotlin.math.log10
import kotlin.math.pow

private var RAND_MAX: Long = 32767
private var seed = AtomicLong(System.nanoTime())

fun Set_RAND_MAX(value: Long) {
    RAND_MAX = value
}
fun NewRand(): Long {
    var ticks: Long = System.nanoTime()
    seed.set(ticks)
    var value: Long = (8253739L * seed.get() + 2396403L)
    if (value < 0L) {
        value *= -1L;
    }
    return (value % RAND_MAX)
}

fun Randint(min: Int, max: Int): Int {
    if (min > max) {
        var min1: Int = min;
        var max1: Int = max;
        var max = min1;
        var min = max1
    }
    return (NewRand().toInt() % ((max - min + 1))) + min
}

fun Randshort(min: Short, max: Short): UShort {
    var value: Short
    if(min > max) {
        var min1: Short = min;
        var max1: Short = max;
        var max = min1;
        var min = max1
    }
    var r: Short = NewRand().toShort()
    value = (r % (max - min + 1)).toShort()
    return value.toUShort()

}
fun RandLong(min: Long, max: Long): ULong {
    var value: Long
    if(min > max) {
        var min1: Long = min;
        var max1: Long = max;
        var max = min1;
        var min = max1
    }
    var r: Short = NewRand().toShort()
    value = (r % (max - min + 1))
    return value.toULong()
}

fun Randfloat(min: Double, max: Double): Float {
    if (max < min) {
        var max1: Double = min
        var min1: Double = max
        var max = min1
        var min = max1
    }
    val r = NewRand().toDouble()
    val pre = Randint(min.toInt(), max.toInt())
    val post = r / 10.0.pow(log10(RAND_MAX.toDouble()))
    return pre.toFloat() + post.toFloat()
}

fun Randdouble(min: Double, max: Double): Double {
    if (max < min) {
        var max1: Double = min
        var min1: Double = max
        var max = min1
        var min = max1
    }
    val r = NewRand().toDouble()
    val pre = Randint(min.toInt(), max.toInt())
    val post = r / 10.0.pow(log10(RAND_MAX.toDouble()))
    return pre.toDouble() + post.toDouble()
}

fun Randbool(): Int {
    return if (1 + newRand() % 2 == 1L) 0 else 1
}

fun Randchar(excludeWhitespaces: Boolean): Char {
    val whitespaces = charArrayOf('\t', '\n', ' ')
    if (excludeWhitespaces) {
        return Randint(33, 126).toChar()
    } else {
        return if (true) Randint(33, 126).toChar() else whitespaces[Randint(0, whitespaces.size - 1)]
    }
}
