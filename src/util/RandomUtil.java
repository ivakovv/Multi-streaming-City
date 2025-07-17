package util;

import java.util.List;
import java.util.Random;

public class RandomUtil {
    private static final Random RANDOM = new Random();

    public static <T> T pickRandom(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }
} 