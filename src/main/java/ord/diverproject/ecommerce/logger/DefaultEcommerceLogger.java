package ord.diverproject.ecommerce.logger;

import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NONE;

import java.io.File;

import org.diverproject.scarlet.logger.LoggerLevel;
import org.diverproject.scarlet.logger.abstraction.DefaultLoggerLanguage;
import org.diverproject.scarlet.logger.message.AdvancedMessage;
import org.diverproject.scarlet.util.StringUtils;

import ord.diverproject.ecommerce.Ecommerce;
import ord.diverproject.ecommerce.config.EcommerceSettings;

public class DefaultEcommerceLogger extends DefaultLoggerLanguage implements EcommerceLogger
{
	public DefaultEcommerceLogger(String name)
	{
		super(name, buildPathname(name));
	}

	@Override
	protected void internalLogger(LoggerLevel level, String message, StackTraceElement origin)
	{
		if ((level != null) && (level != NONE))
			this.feedLine();

		if (origin == null)
			origin = this.buildOrigin(1);

		AdvancedMessage loggerMessage = new AdvancedMessage()
		{
			@Override
			public String getOutput()
			{
				if (this.hasOrigin())
				{
					if (this.hasLevel())
						return String.format("[%s] %s | %s.%s:%d - %s",
								this.getLevel().getFormat(),
								this.getDateFormatted(),
								StringUtils.getSimpleNameOf(this.getOrigin().getClassName()),
								this.getOrigin().getMethodName(),
								this.getOrigin().getLineNumber(),
								this.getMessage()
							);

					return String.format("%s | %s.%s:%d - %s",
							this.getDateFormatted(),
							StringUtils.getSimpleNameOf(this.getOrigin().getClassName()),
							this.getOrigin().getMethodName(),
							this.getOrigin().getLineNumber(),
							this.getMessage()
						);
				}

				if (this.hasLevel())
					return String.format(
							"[%s] %s | %s",
							this.getLevel().getFormat(),
							this.getDateFormatted(),
							this.getMessage()
						);

				return String.format(
						"%s | %s",
						this.getDateFormatted(),
						this.getMessage()
					);
			}
		};
		loggerMessage.setDateFormatted(this.getCurrentDateFormatted());
		loggerMessage.setLevel(level);
		loggerMessage.setMessage(message);
		loggerMessage.setOrigin(origin);

		this.log(loggerMessage);
	}

	private static String buildPathname(String name)
	{
		EcommerceSettings settings = Ecommerce.getInstance().getEcommerceSettings();
		String folderPath = settings.getLoggerFolderPath();
		String filename = String.format("%s_%s.log", name, settings.currentDate());
		File file = new File(folderPath, filename);
		file.getParentFile().mkdirs();

		return file.getAbsolutePath();
	}
}
