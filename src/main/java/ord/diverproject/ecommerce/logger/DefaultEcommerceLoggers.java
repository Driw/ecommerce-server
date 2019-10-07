package ord.diverproject.ecommerce.logger;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

import org.diverproject.scarlet.logger.LoggerLanguage;
import org.diverproject.scarlet.logger.Loggers;
import org.diverproject.scarlet.logger.ScarletLoggers;

import ord.diverproject.ecommerce.EcommerceRuntimeException;
import ord.diverproject.ecommerce.language.EcommerceLanguage;

public class DefaultEcommerceLoggers implements Loggers<EcommerceLogger>
{
	private static final String DEFAULT_NAME = "default";
	private static final ScarletLoggers SCARLET_LOGGER = ScarletLoggers.getInstance();

	@Override
	public EcommerceLogger get(Class<?> classz)
	{
		return this.get(classz.getName());
	}

	@Override
	public EcommerceLogger get(String name)
	{
		LoggerLanguage logger = SCARLET_LOGGER.get(name);

		if (logger == null)
			logger = SCARLET_LOGGER.add(DefaultEcommerceLogger.class);

		if (!(logger instanceof EcommerceLogger))
			throw new EcommerceRuntimeException(EcommerceLanguage.ECOMMERCE_LOGGER_GET, name, nameOf(logger));

		return (EcommerceLogger) logger;
	}

	@Override
	public EcommerceLogger getDefault()
	{
		return this.get(DEFAULT_NAME);
	}
}
