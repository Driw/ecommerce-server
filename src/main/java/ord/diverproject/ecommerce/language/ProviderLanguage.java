package ord.diverproject.ecommerce.language;

import static ord.diverproject.ecommerce.Constants.PROVIDER_CNPJ_INVALID;
import static ord.diverproject.ecommerce.Constants.PROVIDER_CNPJ_REQUIRED;
import static ord.diverproject.ecommerce.Constants.PROVIDER_CNPJ_UNAVAIABLE;
import static ord.diverproject.ecommerce.Constants.PROVIDER_COMPANY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.PROVIDER_COMPANY_NAME_REQUIRED;
import static ord.diverproject.ecommerce.Constants.PROVIDER_FAILURE_ON_ADD;
import static ord.diverproject.ecommerce.Constants.PROVIDER_FAILURE_ON_SET;
import static ord.diverproject.ecommerce.Constants.PROVIDER_FANTASY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.PROVIDER_FANTASY_NAME_REQUIRED;
import static ord.diverproject.ecommerce.Constants.PROVIDER_NOT_EXISTS;
import static ord.diverproject.ecommerce.Constants.PROVIDER_SITE_URL_LENGTH;
import static ord.diverproject.ecommerce.Constants.PROVIDER_SPOKESMAN_LENGTH;

import org.diverproject.scarlet.language.Language;

public enum ProviderLanguage implements Language
{
	CNPJ_REQUIRED			("CNPJ is required", PROVIDER_CNPJ_REQUIRED),
	COMPANY_NAME_REQUIRED	("company name is required", PROVIDER_COMPANY_NAME_REQUIRED),
	FANTASY_NAME_REQUIRED	("fantasy name is required", PROVIDER_FANTASY_NAME_REQUIRED),

	CNPJ_INVALID			("invalid CNPJ '%s'", PROVIDER_CNPJ_INVALID),
	COMPANY_NAME_LENGTH		("company name need have %d~%d characters (companyName: %s)", PROVIDER_COMPANY_NAME_LENGTH),
	FANTASY_NAME_LENGTH		("fantasy name need have %d~%d characters (fantasyName: %s)", PROVIDER_FANTASY_NAME_LENGTH),
	SPOKESPAMN_LENGTH		("spokesman need have %d~%d characteres (spokesman: %s)", PROVIDER_SPOKESMAN_LENGTH),
	SITE_URL_LENGTH			("site URL need have %d~%d characteres (spokesman: %s)", PROVIDER_SITE_URL_LENGTH),

	FAILURE_ON_ADD			("failure on add '%s' provider - reason: %s", PROVIDER_FAILURE_ON_ADD),
	FAILURE_ON_SET			("failure on set '%s' provider - reason: %s", PROVIDER_FAILURE_ON_SET),
	CNPJ_UNAVAIABLE			("CNPJ '%s' unavaiable", PROVIDER_CNPJ_UNAVAIABLE),
	NOT_EXISTS				("provider not found", PROVIDER_NOT_EXISTS),

	;

	private final int code;
	private String format;

	private ProviderLanguage(String format, int code)
	{
		this.code = code;
		this.setFormat(format);
	}

	@Override
	public int getCode()
	{
		return this.code;
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
