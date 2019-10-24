package org.diverproject.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.diverproject.ecommerce.config.EcommerceSettings;

public class Ecommerce
{
	private static final Ecommerce INSTANCE = new Ecommerce();

	@Autowired
	private EcommerceSettings ecommerceSettings;
	private Logger ecommerceLoggers = LoggerFactory.getLogger(Ecommerce.class);

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

	public Logger getEcommerceLoggers()
	{
		return this.ecommerceLoggers;
	}

	public static Ecommerce getInstance()
	{
		return INSTANCE;
	}
}
