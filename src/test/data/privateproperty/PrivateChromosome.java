package test.data.privateproperty;

import main.core.definitions.Individual;

public class PrivateChromosome implements Individual {

	private PrivateProperty chromosome;

	@Override
	public Double fitness() {
		return null;
	}

	public PrivateProperty getChromosome() {
		return chromosome;
	}

}
