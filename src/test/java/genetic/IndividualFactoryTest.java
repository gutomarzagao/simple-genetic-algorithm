package genetic;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import core.genetic.IndividualFactory;
import data.DataTypesSupported;
import data.EnumType;
import data.UnknownGeneType;
import data.customtype.CustomIndividual;
import data.gene.GeneLimits;
import data.nullable.NullableIndividual;
import data.privateproperty.PrivateChromosome;
import data.reputation.CityReputation;
import shell.util.Rand;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Rand.class)
public class IndividualFactoryTest {

	@Test
	public void supportedDataTypes() throws InstantiationException, IllegalAccessException {
		// Arrange
		PowerMockito.mockStatic(Rand.class);
		PowerMockito.when(Rand.getInt(0, Integer.MAX_VALUE - 1)).thenReturn(43);
		PowerMockito.when(Rand.getInt(0, 2)).thenReturn(2);

		// Act
		DataTypesSupported supportedDataTypes = IndividualFactory.createRandom(DataTypesSupported.class);

		// Assert
		Assert.assertEquals(43, supportedDataTypes.integer);
		Assert.assertEquals(EnumType.C, supportedDataTypes.enumeration);
	}

	@Test
	public void customDataTypes() throws InstantiationException, IllegalAccessException {
		// Arrange
		PowerMockito.mockStatic(Rand.class);
		PowerMockito.when(Rand.getInt(0, Integer.MAX_VALUE - 1)).thenReturn(43);
		PowerMockito.when(Rand.getInt(0, 2)).thenReturn(2);

		// Act
		CustomIndividual customDataTypes = IndividualFactory.createRandom(CustomIndividual.class);

		// Assert
		Assert.assertEquals(new BigDecimal(11), customDataTypes.customType);
	}

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

	@Test
	public void nullableChromosome() throws InstantiationException, IllegalAccessException {
		// Arrange
		PowerMockito.mockStatic(Rand.class);
		PowerMockito.when(Rand.getBoolean()).thenReturn(true);

		// Act
		NullableIndividual individual = IndividualFactory.createRandom(NullableIndividual.class);

		// Assert
		Assert.assertEquals(null, individual.chromosome);
	}

	@Test
	public void nullableType() throws InstantiationException, IllegalAccessException {
		// Arrange
		PowerMockito.mockStatic(Rand.class);
		PowerMockito.when(Rand.getBoolean()).thenReturn(false).thenReturn(true);

		// Act
		NullableIndividual individual = IndividualFactory.createRandom(NullableIndividual.class);

		// Assert
		Assert.assertEquals(null, individual.chromosome.nullableType);
	}

}
