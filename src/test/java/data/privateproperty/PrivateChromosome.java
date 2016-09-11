package data.privateproperty;

import core.definitions.Individual;

public class PrivateChromosome implements Individual<PrivateChromosome> {

	private PrivateProperty chromosome;

	@Override
	public int compareTo(PrivateChromosome o) {
		return 0;
	}
	
	public PrivateProperty getChromosome() {
		return chromosome;
	}

}
