package test.java.data.privateproperty;

import main.java.core.definitions.Individual;

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
