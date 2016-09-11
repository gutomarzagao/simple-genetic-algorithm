package data.reputation;

import core.definitions.Individual;

public class CityReputation implements Individual<CityReputation> {

	public int reputation;

	public StateReputation state;

	@Override
	public int compareTo(CityReputation o) {
		return 0;
	}

}
