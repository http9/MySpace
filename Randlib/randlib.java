import java.util.concurrent.atomic.AtomicLong;

public class randlib {
    private static long RAND_MAX = 32767;
    private static final AtomicLong seed = new AtomicLong(System.nanoTime());

    public randlib() {
    }

    public static void Set_Rand_Max(long value) {
        RAND_MAX = value;
    }

    public static int NewRand() {
        long ticks = System.nanoTime();
        seed.set(ticks);
        long value = (long)((int)(8253739L * seed.get() + 2396403L));
        if (value < 0L) {
            value *= -1L;
        }

        value = (long)((int)(value % RAND_MAX));
        return (int)value;
    }

    public static int Randint(int min, int max) {
        long value = 0L;
        if (max < min) {
            int max1 = max;
            max = min;
            min = max1;
        }

        long r = (long)NewRand();
        value = (r % (long)(max - min + 1)) + min;
        if (value < 0L) {
            value *= -1L;
        }

        return (int)value;
    }

    public static int Randshort(short min, short max) {
        long value = 0L;
        if (max < min) {
            short max1 = max;
            max = min;
            min = max1;
        }

        long r = (long)NewRand();
        value = r % (long)(max - min + 1);
        if (value < 0L) {
            value *= -1L;
        }

        return (short)((int)value);
    }

    public static int Randlong(long min, long max) {
        long value = 0L;
        if (max < min) {
            long max1 = max;
            max = min;
            min = max1;
        }

        long r = (long)NewRand();
        value = r % (max - min + 1L);
        if (value < 0L) {
            value *= -1L;
        }

        return (int)value;
    }

    public static float Randfloat(float min, float max) {
        if (max < min) {
            float max1 = max;
            max = min;
            min = max1;
        }

        double r = (double)((float)NewRand());
        long pre = (long)Randint((int)min, (int)max - 1);
        double post = r / Math.pow(10.0, (double)((float)Math.log10((double)RAND_MAX)));
        return (float)pre + (float)post;
    }

    public static double Randdouble(double min, double max) {
        double post;
        if (max < min) {
            post = max;
            max = min;
            min = post;
        }

        double r = (double)NewRand();
        long pre = (long)Randint((int)min, (int)max - 1);
        post = r / Math.pow(10.0, Math.log10((double)RAND_MAX));
        return (double)pre + post;
    }

    public static int Randbool() {
        return NewRand() % 5 == 1 ? 0 : 1;
    }

    private static int Randintbool(int min, int max) {
        long value = 0L;
        if (max < min) {
            int max1 = max;
            max = min;
            min = max1;
        }

        long r = (long)NewRand();
        value = r % (long)(max - min + 1);
        if (value < 0L) {
            value *= -1L;
        }

        return (int)value;
    }

    public static char Randchar(boolean excludeWhitespace) {
        char[] var10000 = new char[]{'\t', '\n', ' '};
        return excludeWhitespace ? (char)Randint(33, 126) : (char)Randint(33, 126);
    }
}
