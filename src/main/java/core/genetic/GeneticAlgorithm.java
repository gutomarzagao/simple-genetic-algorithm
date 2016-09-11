package core.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.definitions.Individual;
import shell.util.Rand;

public class GeneticAlgorithm {

	public static <T extends Individual<T>> List<T> run(GeneticParams<T> params)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		List<T> population = new ArrayList<>();
		addRandomIndividuals(population, params);

		for (int curGeneration = 0; curGeneration < params.getGenerations(); curGeneration++) {
			List<T> nextGeneration = new ArrayList<>();
			Collections.sort(population, Collections.reverseOrder());

			addEliteIndividuals(population, nextGeneration, params);
			addCrossingIndividuals(population, nextGeneration, params);
			addRandomIndividuals(nextGeneration, params);

			population = nextGeneration;
		}

		return population;
	}

	private static <T extends Individual<T>> void addEliteIndividuals(List<T> population, List<T> nextGeneration,
			GeneticParams<T> params) {
		for (int i = 0; i < params.getEliteSize(); i++) {
			nextGeneration.add(population.get(i));
		}
	}

	private static <T extends Individual<T>> void addCrossingIndividuals(List<T> population, List<T> nextGeneration,
			GeneticParams<T> params) throws InstantiationException, IllegalAccessException {
		for (int i = 0; i < params.getCrossingSize(); i++) {
			Individual<T> parent1 = Rand.getListElement(population);
			Individual<T> parent2 = Rand.getListElement(population);

			T child = IndividualFactory.crossbreed(params.getIndividual(), parent1, parent2);
			child = IndividualFactory.mutate(params.getIndividual(), child);
			nextGeneration.add(child);
		}
	}

	private static <T extends Individual<T>> void addRandomIndividuals(List<T> population, GeneticParams<T> params)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		while (population.size() < params.getTotalPopulation()) {
			T individual = IndividualFactory.createRandom(params.getIndividual());
			population.add(individual);
		}
	}

}
