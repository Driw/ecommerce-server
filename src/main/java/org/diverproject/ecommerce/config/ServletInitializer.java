package org.diverproject.ecommerce.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.diverproject.ecommerce.EcommerceApplication;

public class ServletInitializer extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(EcommerceApplication.class);
	}
}
