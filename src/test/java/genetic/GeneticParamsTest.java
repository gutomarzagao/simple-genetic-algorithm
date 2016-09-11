package genetic;

import org.junit.Test;

import core.definitions.Individual;
import core.genetic.GeneticParams;
import data.UnknownGeneType;
import junit.framework.Assert;

public class GeneticParamsTest {

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionForIndividualClass() throws InstantiationException, IllegalAccessException {
		// Act
		new GeneticParams<>(Individual.class);
	}

	@Test
	public void calculatePopulationSize() throws InstantiationException, IllegalAccessException {
		// Arrange
		GeneticParams<UnknownGeneType> params = new GeneticParams<>(UnknownGeneType.class);
		params.setEliteSize(1);
		params.setCrossingSize(2);
		params.setRandomSize(3);

		// Act & Assert
		Assert.assertEquals(6, params.getTotalPopulation());
	}

}
