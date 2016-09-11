package shell.util;

import java.util.Random;

public class Rand {

	private static Random random = new Random();

	public static boolean getBoolean() {
		return random.nextBoolean();
	}

	public static boolean getBoolean(double probability) {
		if (probability < 0 || probability > 1) {
			throw new IllegalArgumentException("Probability should be a number between 0 and 1");
		}
		
		return random.nextDouble() < probability;
	}

	public static int getInt(int lowerValue, int upperValue) {
		if (upperValue < lowerValue || upperValue - lowerValue < 0) {
			throw new IllegalArgumentException("Lower value cannot be greater than upper value");
		}

		return random.nextInt(upperValue - lowerValue + 1) + lowerValue;
	}

}
