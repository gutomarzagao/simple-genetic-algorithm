package data.nullable;

import core.definitions.Chromosome;
import core.gene.GeneNullable;
import data.EnumType;

public class NullableChromosome implements Chromosome {

	@GeneNullable
	public EnumType nullableType;

}
