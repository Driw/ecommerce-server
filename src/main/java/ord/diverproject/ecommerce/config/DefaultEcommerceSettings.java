package ord.diverproject.ecommerce.config;

public class DefaultEcommerceSettings implements EcommerceSettings
{
	private String loggerFolderPath;
	private String currentDate;

	@Override
	public String getLoggerFolderPath()
	{
		return this.loggerFolderPath;
	}

	public void setLoggerFolderPath(String loggerFolderPath)
	{
		this.loggerFolderPath = loggerFolderPath;
	}

	@Override
	public String currentDate()
	{
		return this.currentDate;
	}
}
