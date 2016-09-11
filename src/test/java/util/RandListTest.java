package util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import shell.util.Rand;

public class RandListTest {

	@Test
	public void firstListElement() {
		// Arrange
		Integer numbers[] = { 6, 8, 5, 9, 1, 2 };
		List<Integer> list = Arrays.asList(numbers);

		Random random = Mockito.mock(Random.class);
		Mockito.when(random.nextInt(6)).thenReturn(0);

		Whitebox.setInternalState(Rand.class, "random", random);

		// Act & Assert
		Assert.assertEquals(6, (long) Rand.getListElement(list));
	}
	
	@Test
	public void lastListElement() {
		// Arrange
		Integer numbers[] = { 6, 8, 5, 9, 1, 2 };
		List<Integer> list = Arrays.asList(numbers);
		
		Random random = Mockito.mock(Random.class);
		Mockito.when(random.nextInt(6)).thenReturn(5);
		
		Whitebox.setInternalState(Rand.class, "random", random);
		
		// Act & Assert
		Assert.assertEquals(2, (long) Rand.getListElement(list));
	}

}
