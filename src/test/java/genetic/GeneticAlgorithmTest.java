package genetic;

import org.junit.Test;

import core.genetic.GeneticAlgorithm;
import core.genetic.GeneticParams;
import data.reputation.CityReputation;

public class GeneticAlgorithmTest {

	@Test(expected = IllegalArgumentException.class)
	public void algorithmImplementationIsIncomplete()
			throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		// Arrange
		GeneticParams<CityReputation> params = new GeneticParams<>(CityReputation.class);
		params.setEliteSize(1);
		params.setCrossingSize(2);
		params.setRandomSize(3);

		// Act
		GeneticAlgorithm.run(params);
	}

}
