package data.reputation;

import core.definitions.Individual;

public class CityReputation implements Individual {

	public int reputation;

	public StateReputation state;

	@Override
	public int compareTo(Individual o) {
		return 0;
	}

}
