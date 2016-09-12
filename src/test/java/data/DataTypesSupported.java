package data;

import core.definitions.Individual;

public class DataTypesSupported implements Individual<DataTypesSupported> {

	public int integer;
	public EnumType enumeration;

	@Override
	public int compareTo(DataTypesSupported o) {
		return 0;
	}

}
