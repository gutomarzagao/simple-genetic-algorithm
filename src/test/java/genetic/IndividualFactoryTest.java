package genetic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import core.genetic.IndividualFactory;
import data.UnknownGeneType;
import data.gene.GeneLimits;
import data.privateproperty.PrivateChromosome;
import data.reputation.CityReputation;
import shell.util.Rand;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Rand.class)
public class IndividualFactoryTest {

	@Test
	public void createRandomIndividual() throws InstantiationException, IllegalAccessException {
		// Arrange
		PowerMockito.mockStatic(Rand.class);
		PowerMockito.when(Rand.getInt(0, Integer.MAX_VALUE - 1)).thenReturn(35).thenReturn(25).thenReturn(15);

		// Act
		CityReputation cityReputation = IndividualFactory.createRandom(CityReputation.class);

		// Assert
		Assert.assertEquals(35, cityReputation.reputation);
		Assert.assertEquals(25, cityReputation.state.reputation);
		Assert.assertEquals(15, cityReputation.state.country.reputation);
	}

	@Test
	public void randomIntegerShouldRespectGeneLimits() throws InstantiationException, IllegalAccessException {
		// Arrange
		PowerMockito.mockStatic(Rand.class);
		PowerMockito.when(Rand.getInt(33, 77)).thenReturn(33);
		PowerMockito.when(Rand.getInt(64, 92)).thenReturn(92);

		// Act
		GeneLimits geneLimits = IndividualFactory.createRandom(GeneLimits.class);

		// Assert
		Assert.assertEquals(33, geneLimits.minValue);
		Assert.assertEquals(92, geneLimits.maxValue);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionForUnknownGeneType() throws InstantiationException, IllegalAccessException {
		// Act
		IndividualFactory.createRandom(UnknownGeneType.class);
	}

	@Test
	public void canAccessPrivateProperties() throws InstantiationException, IllegalAccessException {
		// Arrange
		PowerMockito.mockStatic(Rand.class);
		PowerMockito.when(Rand.getInt(0, Integer.MAX_VALUE - 1)).thenReturn(50);

		// Act
		PrivateChromosome privateChromosome = IndividualFactory.createRandom(PrivateChromosome.class);

		// Assert
		Assert.assertEquals(50, privateChromosome.getChromosome().getProperty());
	}

}
