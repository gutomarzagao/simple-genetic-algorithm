package core.gene;

import java.util.HashMap;

public interface GeneFactory<T> {

	T create(HashMap<String, Integer> argsInteger);

}
