import java.util.Random;

public class RandomIdGenerator implements IdGenerator {
    private final Random rnd;

    public RandomIdGenerator() {
        this.rnd = new Random();
    }

    public RandomIdGenerator(long seed) {
        this.rnd = new Random(seed);
    }

    @Override
    public String generate() {
        return "H-" + (7000 + rnd.nextInt(1000));
    }
}