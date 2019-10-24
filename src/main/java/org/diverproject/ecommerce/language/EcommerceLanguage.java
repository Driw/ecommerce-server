package org.diverproject.ecommerce.language;

import org.diverproject.scarlet.language.Language;

public enum EcommerceLanguage implements Language
{
	ECOMMERCE_LOGGER_GET			("cannot get '%s' logger bacause it's not a instance of '%s'"),
	VALIDATE_REQUIRED_FIELDS		("failure on validate required fields - reason: %s"),
	HANDLE_CONSTRAINTS				("one or more fields not were not met with the constraints"),

	;

	private String format;

	private EcommerceLanguage(String format)
	{
		this.setFormat(format);
	}

	@Override
	public int getCode()
	{
		return this.ordinal();
	}

	@Override
	public String getFormat()
	{
		return this.format;
	}

	@Override
	public void setFormat(String format)
	{
		this.format = format;
	}
}
