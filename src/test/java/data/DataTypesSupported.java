package data;

import core.definitions.Individual;

public class DataTypesSupported implements Individual<DataTypesSupported> {

	public int integer;
	public Enum enumeration;

	public enum Enum {
		A, B, C
	}

	@Override
	public int compareTo(DataTypesSupported o) {
		return 0;
	}

}
