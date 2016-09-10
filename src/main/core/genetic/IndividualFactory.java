package main.core.genetic;

import java.lang.reflect.Field;

import main.core.definitions.Chromosome;
import main.core.definitions.Individual;
import main.core.definitions.Traversable;
import main.shell.util.Rand;

public class IndividualFactory {

	public static <T extends Individual> T createRandom(Class<T> individual)
			throws InstantiationException, IllegalAccessException {
		validateClass(individual);
		return traverse(individual, OperationType.RANDOM);
	}

	private static <T extends Individual> void validateClass(Class<T> individual) {
		if (individual.equals(Individual.class)) {
			throw new IllegalArgumentException(String.format("Invalid class type: %s", individual.getName()));
		}
	}

	@SuppressWarnings("unchecked")
	private static <T extends S, S extends Traversable> T traverse(Class<T> traversable, OperationType operation,
			Object... args) throws InstantiationException, IllegalAccessException {
		T instance = traversable.newInstance();
		Field[] fields = traversable.getDeclaredFields();

		for (Field field : fields) {
			Class<?> fieldType = field.getType();

			if (Chromosome.class.isAssignableFrom(fieldType)) {
				Chromosome chromosome = traverse((Class<? extends Chromosome>) fieldType, operation, args);
				field.setAccessible(true);
				field.set(instance, chromosome);
			} else {
				switch (operation) {
				case CROSSBREED:
					Object inheritedGene = crossbreed(field, args);
					field.set(instance, inheritedGene);
					break;

				case MUTATE:
					Object mutatedGene = mutate(field, args);
					field.set(instance, mutatedGene);
					break;

				case RANDOM:
					Object randomGene = random(field, args);
					field.setAccessible(true);
					field.set(instance, randomGene);
					break;

				default:
					throw new IllegalArgumentException("Invalid operation type");
				}
			}
		}

		return instance;
	}

	private static Object crossbreed(Field field, Object... args) {
		throw new IllegalArgumentException("Not implemented");
	}

	private static Object mutate(Field field, Object... args) {
		throw new IllegalArgumentException("Not implemented");
	}

	private static Object random(Field field, Object... args) {
		if (args.length != 0) {
			throw new IllegalArgumentException("No arguments are allowed for this operation");
		}

		Class<?> fieldType = field.getType();

		if (fieldType.equals(int.class)) {
			int minValue = 0;
			int maxValue = Integer.MAX_VALUE - 1;

			return Rand.getInt(minValue, maxValue);
		} else {
			throw new IllegalArgumentException(String.format("Cannot create a value for the type %s", fieldType));
		}
	}

}
