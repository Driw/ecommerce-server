package ord.diverproject.ecommerce.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstraintErrorCode
{
	public int notBlankCode() default 0;
	public int sizeCode() default 0;
	public int patternCode() default 0;
}
