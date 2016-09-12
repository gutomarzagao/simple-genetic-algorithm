package core.gene;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import core.attributes.ArgInteger;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Gene {

	public Class<? extends GeneFactory<?>> factory();

	public ArgInteger[] argsInteger() default {};

}
