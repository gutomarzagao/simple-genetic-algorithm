package data.privateproperty;

import core.definitions.Individual;

public class PrivateChromosome implements Individual {

	private PrivateProperty chromosome;

	@Override
	public int compareTo(Individual o) {
		return 0;
	}
	
	public PrivateProperty getChromosome() {
		return chromosome;
	}

}
