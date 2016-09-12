package data.customtype;

import java.math.BigDecimal;
import java.util.HashMap;

import core.gene.GeneFactory;

public class BigDecimalFactory implements GeneFactory<BigDecimal> {

	@Override
	public BigDecimal create(HashMap<String, Integer> argsInteger) {
		Integer value = argsInteger.get("Fixed Value");
		return new BigDecimal(value);
	}

}
