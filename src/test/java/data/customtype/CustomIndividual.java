package data.customtype;

import java.math.BigDecimal;

import core.attributes.ArgInteger;
import core.definitions.Individual;
import core.gene.Gene;

public class CustomIndividual implements Individual<CustomIndividual> {

	@Gene(factory = BigDecimalFactory.class, argsInteger = { @ArgInteger(name = "Fixed Value", value = 11) })
	public BigDecimal customType;

	@Override
	public int compareTo(CustomIndividual arg0) {
		return 0;
	}

}
