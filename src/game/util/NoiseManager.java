package game.util;


public class NoiseManager {

    public static OpenSimplexNoise noise;
    public static int seed;

    public static void setupNoise(int seed) {
        noise = new OpenSimplexNoise(seed);

        NoiseManager.seed = seed;
    }

    public static double getNoise(int num_iterations, int x, int y, double persistence, double scale, double low, double high) {
        double maxAmp = 0;
        double amp = 1;
        double freq = scale;
        double n = 0;

        for (int i = 0; i < num_iterations; i++) {
            n += noise.eval(x * freq, y * freq) * amp;
            maxAmp += amp;
            amp *= persistence;
            freq *= 2;
        }

        n /= maxAmp;

        n = n * (high - low) / 2 + (high + low) / 2;

        return n;
    }
}
