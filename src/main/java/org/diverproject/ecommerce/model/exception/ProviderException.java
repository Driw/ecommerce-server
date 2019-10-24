package org.diverproject.ecommerce.model.exception;

import org.diverproject.ecommerce.EcommerceRuntimeException;
import org.diverproject.ecommerce.language.ProviderLanguage;
import org.diverproject.ecommerce.model.Provider;

public class ProviderException extends EcommerceRuntimeException
{
	private static final long serialVersionUID = 5205812611417494171L;

	private ProviderException(ProviderLanguage language)
	{
		super(language);
	}

	private ProviderException(ProviderLanguage language, Object... args)
	{
		super(language, args);
	}

	private ProviderException(Exception e)
	{
		super(e);
	}

	private ProviderException(Exception e, ProviderLanguage language)
	{
		super(e, language);
	}

	private ProviderException(Exception e, ProviderLanguage language, Object... args)
	{
		super(e, language, args);
	}

	public static ProviderException newCnpjInvalid(Provider provider)
	{
		return new ProviderException(ProviderLanguage.CNPJ_INVALID, provider.getCnpj());
	}

	public static ProviderException newFailureOnAdd(Exception e, Provider provider)
	{
		return new ProviderException(ProviderLanguage.FAILURE_ON_ADD, provider.getCompanyName(), e.getMessage());
	}

	public static ProviderException newFailureOnSet(Exception e, Provider provider)
	{
		return new ProviderException(ProviderLanguage.FAILURE_ON_SET, provider.getCompanyName(), e.getMessage());
	}

	public static ProviderException newCnpjUnavaiable(Provider provider)
	{
		return new ProviderException(ProviderLanguage.CNPJ_UNAVAIABLE, provider.getCnpj());
	}

	public static ProviderException newNotExists()
	{
		return new ProviderException(ProviderLanguage.NOT_EXISTS);
	}
}
