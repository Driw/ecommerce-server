package ord.diverproject.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;

import ord.diverproject.ecommerce.config.EcommerceSettings;
import ord.diverproject.ecommerce.logger.EcommerceLoggers;

public class Ecommerce
{
	private static final Ecommerce INSTANCE = new Ecommerce();

	@Autowired
	private EcommerceSettings ecommerceSettings;
	@Autowired
	private EcommerceLoggers ecommerceLoggers;

	private Ecommerce()
	{
		this.init();
	}

	private void init()
	{
		System.out.println(this.getEcommerceLoggers());
		System.out.println(this.getEcommerceSettings());
	}

	public EcommerceSettings getEcommerceSettings()
	{
		return this.ecommerceSettings;
	}

	public EcommerceLoggers getEcommerceLoggers()
	{
		return this.ecommerceLoggers;
	}

	public static Ecommerce getInstance()
	{
		return INSTANCE;
	}
}
