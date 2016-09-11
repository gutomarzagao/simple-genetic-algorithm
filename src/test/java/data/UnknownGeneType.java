package data;

import core.definitions.Individual;

public class UnknownGeneType implements Individual {

	public boolean unknownType;

	@Override
	public Double fitness() {
		return null;
	}

}
