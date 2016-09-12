package data.nullable;

import core.definitions.Individual;
import core.gene.GeneNullable;

public class NullableIndividual implements Individual<NullableIndividual> {

	@GeneNullable
	public NullableChromosome chromosome;

	@Override
	public int compareTo(NullableIndividual o) {
		return 0;
	}
}
