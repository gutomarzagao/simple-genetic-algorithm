package util;

import org.junit.Assert;
import org.junit.Test;

import shell.util.Rand;

public class RandBooleanTest {

	@Test
	public void booleanTrue() {
		// Act & Assert
		Assert.assertEquals(true, Rand.getBoolean(1));
	}

	@Test
	public void booleanFalse() {
		// Act & Assert
		Assert.assertEquals(false, Rand.getBoolean(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void probabilityCannotBeLessThanZero() {
		// Act
		Rand.getBoolean(-0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public void probabilityCannotBeGreaterThanOne() {
		// Act
		Rand.getBoolean(1.01);
	}

}
