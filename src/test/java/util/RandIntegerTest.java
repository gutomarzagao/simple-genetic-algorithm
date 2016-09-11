package util;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import shell.util.Rand;

public class RandIntegerTest {

	@Test
	public void lowestInteger() {
		// Arrange
		Random random = Mockito.mock(Random.class);
		Mockito.when(random.nextInt(657)).thenReturn(0);

		Whitebox.setInternalState(Rand.class, "random", random);

		// Act & Assert
		Assert.assertEquals(36, Rand.getInt(36, 692));
	}

	@Test
	public void highestInteger() {
		// Arrange
		Random random = Mockito.mock(Random.class);
		Mockito.when(random.nextInt(657)).thenReturn(656);

		Whitebox.setInternalState(Rand.class, "random", random);

		// Act & Assert
		Assert.assertEquals(692, Rand.getInt(36, 692));
	}

	@Test
	public void negativeIntegerRange() {
		// Arrange
		Random random = Mockito.mock(Random.class);
		Mockito.when(random.nextInt(39)).thenReturn(31);

		Whitebox.setInternalState(Rand.class, "random", random);

		// Act & Assert
		Assert.assertEquals(-93, Rand.getInt(-124, -86));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidOrderOfArguments() {
		// Act
		Rand.getInt(55, 20);
	}

	@Test
	public void minimumInteger() {
		// Act & Assert
		Assert.assertEquals(Integer.MIN_VALUE, Rand.getInt(Integer.MIN_VALUE, Integer.MIN_VALUE));
	}

	@Test
	public void maximumInteger() {
		// Act & Assert
		Assert.assertEquals(Integer.MAX_VALUE, Rand.getInt(Integer.MAX_VALUE, Integer.MAX_VALUE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void maximumIntegerRange() {
		// Act
		Rand.getInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

}
