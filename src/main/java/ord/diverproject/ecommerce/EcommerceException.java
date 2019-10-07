package ord.diverproject.ecommerce;

import org.diverproject.scarlet.ScarletException;
import org.diverproject.scarlet.language.Language;

public class EcommerceException extends ScarletException
{
	private static final long serialVersionUID = 5205812611417494171L;

	public EcommerceException(Language language)
	{
		super(language);
	}

	public EcommerceException(Language language, Object... args)
	{
		super(language, args);
	}

	public EcommerceException(Exception e)
	{
		super(e);
	}

	public EcommerceException(Exception e, Language language)
	{
		super(e, language);
	}

	public EcommerceException(Exception e, Language language, Object... args)
	{
		super(e, language, args);
	}
}
