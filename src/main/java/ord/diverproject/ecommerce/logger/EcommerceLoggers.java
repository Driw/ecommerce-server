package ord.diverproject.ecommerce.logger;

import ord.diverproject.ecommerce.Ecommerce;
import org.diverproject.scarlet.logger.LoggerLanguage;
import org.diverproject.scarlet.logger.Loggers;

public interface EcommerceLoggers extends Loggers<EcommerceLogger>
{
	public EcommerceLogger get(Class<?> classz);
	public EcommerceLogger get(String name);
	public EcommerceLogger getDefault();
}
