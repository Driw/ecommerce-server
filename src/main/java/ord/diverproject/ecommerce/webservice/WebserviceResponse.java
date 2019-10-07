package ord.diverproject.ecommerce.webservice;

import static ord.diverproject.ecommerce.Constants.WS_FAILURE;
import static ord.diverproject.ecommerce.Constants.WS_SUCCESSFULLY;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.util.StringUtils;

import ord.diverproject.ecommerce.Constants;

public class WebserviceResponse<C>
{
	private int code;
	private String message;
	private long duration;
	private C result;

	public WebserviceResponse()
	{
		this.setCode(Constants.WS_FAILURE);
	}

	public int getCode()
	{
		return this.code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMessage(String format, Object... args)
	{
		this.message = String.format(format, args);
	}

	public void setMessage(Language language)
	{
		this.message = language.getFormat();
	}

	public void setMessage(Language language, Object... args)
	{
		this.message = String.format(language.getFormat(), args);
	}

	public long getDuration()
	{
		return this.duration;
	}

	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	public void calculateDuration(long start)
	{
		this.setDuration(System.currentTimeMillis() - start);
	}

	public C getResult()
	{
		return this.result;
	}

	public void setResult(C result)
	{
		this.result = result;
	}

	public void setSuccessfully(String format, Object... args)
	{
		this.setCode(WS_SUCCESSFULLY);
		this.setMessage(format, args);
	}

	public void setFailure(String format, Object... args)
	{
		this.setCode(WS_FAILURE);
		this.setMessage(format, args);
	}

	public ResponseEntity<WebserviceResponse<C>> reponseEntityOk()
	{
		return new ResponseEntity<WebserviceResponse<C>>(this, HttpStatus.OK);
	}

	public ResponseEntity<WebserviceResponse<C>> reponseEntityNotOk()
	{
		return new ResponseEntity<WebserviceResponse<C>>(this, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public String toString()
	{
		return StringUtils.objectToString(
			this,
			"code", this.getCode(),
			"message", this.getMessage(),
			"duration", this.getDuration(),
			"result", this.getResult()
		);
	}
}
