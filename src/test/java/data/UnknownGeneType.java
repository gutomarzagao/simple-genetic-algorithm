package data;

import core.definitions.Individual;

public class UnknownGeneType implements Individual<UnknownGeneType> {

	public boolean unknownType;

	@Override
	public int compareTo(UnknownGeneType o) {
		return 0;
	}

}
