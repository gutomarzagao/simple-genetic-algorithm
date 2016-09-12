package core.genetic;

import java.lang.reflect.Field;
import java.util.HashMap;

import core.attributes.ArgInteger;
import core.definitions.Chromosome;
import core.definitions.Individual;
import core.definitions.Traversable;
import core.gene.Gene;
import core.gene.GeneFactory;
import core.gene.GeneInteger;
import shell.util.Rand;

public class IndividualFactory {

	public static <T extends Individual<T>> T crossbreed(Class<T> individual, Individual<T> parent1,
			Individual<T> parent2) throws InstantiationException, IllegalAccessException {
		return traverse(individual, OperationType.CROSSBREED, parent1, parent2);
	}

	public static <T extends Individual<T>> T mutate(Class<T> individual, Individual<T> mutant)
			throws InstantiationException, IllegalAccessException {
		return traverse(individual, OperationType.MUTATE, mutant);
	}

	public static <T extends Individual<T>> T createRandom(Class<T> individual)
			throws InstantiationException, IllegalAccessException {
		return traverse(individual, OperationType.RANDOM);
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

	private static Object random(Field field, Object... args) throws InstantiationException, IllegalAccessException {
		if (args.length != 0) {
			throw new IllegalArgumentException("No arguments are allowed for this operation");
		}

		Class<?> fieldType = field.getType();

		if (field.isAnnotationPresent(Gene.class)) {
			Gene gene = field.getDeclaredAnnotation(Gene.class);
			Class<? extends GeneFactory<?>> factoryClass = gene.factory();

			HashMap<String, Integer> geneArgs = new HashMap<>();
			ArgInteger[] argsInteger = gene.argsInteger();
			for (ArgInteger argInteger : argsInteger) {
				geneArgs.put(argInteger.name(), argInteger.value());
			}

			GeneFactory<?> factory = factoryClass.newInstance();
			return factory.create(geneArgs);
		} else if (fieldType.equals(int.class)) {
			int minValue = 0;
			int maxValue = Integer.MAX_VALUE - 1;

			if (field.isAnnotationPresent(GeneInteger.class)) {
				GeneInteger geneInteger = field.getDeclaredAnnotation(GeneInteger.class);
				minValue = geneInteger.minValue();
				maxValue = geneInteger.maxValue();
			}

			return Rand.getInt(minValue, maxValue);
		} else if (fieldType.isEnum()) {
			Object[] enumConstants = fieldType.getEnumConstants();
			int value = Rand.getInt(0, enumConstants.length - 1);

			return enumConstants[value];
		} else {
			throw new IllegalArgumentException(String.format("Cannot create a value for the type %s", fieldType));
		}
	}

}
