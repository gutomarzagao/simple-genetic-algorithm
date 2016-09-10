package test.genetic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import main.core.definitions.Individual;
import main.core.genetic.IndividualFactory;
import main.shell.util.Rand;
import test.data.UnknownGeneType;
import test.data.privateproperty.PrivateChromosome;
import test.data.reputation.CityReputation;

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

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionForIndividualClass() throws InstantiationException, IllegalAccessException {
		// Act
		IndividualFactory.createRandom(Individual.class);
	}

}
