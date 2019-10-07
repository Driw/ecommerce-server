package ord.diverproject.ecommerce;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.language.Language;

public class EcommerceRuntimeException extends ScarletRuntimeException
{
	private static final long serialVersionUID = 5205812611417494171L;

	public EcommerceRuntimeException(Language language)
	{
		super(language);
	}

	public EcommerceRuntimeException(Language language, Object... args)
	{
		super(language, args);
	}

	public EcommerceRuntimeException(Exception e)
	{
		super(e);
	}

	public EcommerceRuntimeException(Exception e, Language language)
	{
		super(e, language);
	}

	public EcommerceRuntimeException(Exception e, Language language, Object... args)
	{
		super(e, language, args);
	}
}
