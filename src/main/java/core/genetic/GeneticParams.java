package core.genetic;

import core.definitions.Individual;

public class GeneticParams<T extends Individual<T>> {

	private Class<T> individual;
	private int eliteSize = 50;
	private int crossingSize = 100;
	private int randomSize = 50;
	private int generations = 100;

	public GeneticParams(Class<T> individual) {
		validateClass(individual);
		this.individual = individual;
	}

	private static <T extends Individual<T>> void validateClass(Class<T> individual) {
		if (individual.equals(Individual.class)) {
			throw new IllegalArgumentException(String.format("Invalid class type: %s", individual.getName()));
		}
	}

	public int getTotalPopulation() {
		return eliteSize + crossingSize + randomSize;
	}

	public Class<T> getIndividual() {
		return individual;
	}

	public int getEliteSize() {
		return eliteSize;
	}

	public void setEliteSize(int eliteSize) {
		this.eliteSize = eliteSize;
	}

	public int getCrossingSize() {
		return crossingSize;
	}

	public void setCrossingSize(int crossingSize) {
		this.crossingSize = crossingSize;
	}

	public int getRandomSize() {
		return randomSize;
	}

	public void setRandomSize(int randomSize) {
		this.randomSize = randomSize;
	}

	public int getGenerations() {
		return generations;
	}

	public void setGenerations(int generations) {
		this.generations = generations;
	}

}
