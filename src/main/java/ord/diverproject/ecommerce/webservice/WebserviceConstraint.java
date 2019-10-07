package ord.diverproject.ecommerce.webservice;

import org.diverproject.scarlet.util.StringUtils;

public class WebserviceConstraint
{
	private int code;
	private String field;
	private String violation;

	public int getCode()
	{
		return this.code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getField()
	{
		return this.field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public String getViolation()
	{
		return this.violation;
	}

	public void setViolation(String violation)
	{
		this.violation = violation;
	}

	@Override
	public String toString()
	{
		return StringUtils.objectToString(
			this,
			"code", this.getCode(),
			"field", this.getField(),
			"violation", this.getField()
		);
	}
}
