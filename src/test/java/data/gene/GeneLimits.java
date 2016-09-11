package data.gene;

import core.definitions.GeneInteger;
import core.definitions.Individual;

public class GeneLimits implements Individual<GeneLimits> {

	@GeneInteger(minValue = 33, maxValue = 77)
	public int minValue;

	@GeneInteger(minValue = 64, maxValue = 92)
	public int maxValue;

	@Override
	public int compareTo(GeneLimits o) {
		return 0;
	}

}
