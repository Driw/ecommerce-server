package ord.diverproject.ecommerce;

public class Constants
{
	public static final String HIBERNATE_TYPE_DATE = "DATETIME";
	public static final String HIBERNATE_TYPE_TIMESTAMP = "TIMESTAMP";

	public static final int WS_FAILURE = 0;
	public static final int WS_SUCCESSFULLY = 1;
	public static final int WS_MODEL_CONSTRAINT = 2;
	public static final int WS_MODEL_CONSTRAINT_EXCEPTION = 3;

	public static final int PROVIDER_ALREADY_EXISTS = 101;
	public static final int PROVIDER_NOT_EXISTS = 102;
	public static final int PROVIDER_CNPJ_REQUIRED = 103;
	public static final int PROVIDER_CNPJ_INVALID = 104;
	public static final int PROVIDER_CNPJ_LENGTH = 105;
	public static final int PROVIDER_COMPANY_NAME_REQUIRED = 106;
	public static final int PROVIDER_COMPANY_NAME_INVALID = 107;
	public static final int PROVIDER_COMPANY_NAME_LENGTH = 108;
	public static final int PROVIDER_FANTASY_NAME_REQUIRED = 109;
	public static final int PROVIDER_FANTASY_NAME_INVALID = 110;
	public static final int PROVIDER_FANTASY_NAME_LENGTH = 111;
	public static final int PROVIDER_SPOKESMAN_INVALID = 112;
	public static final int PROVIDER_SPOKESMAN_LENGTH = 113;
	public static final int PROVIDER_SITE_URL_INVALID = 114;
	public static final int PROVIDER_SITE_URL_LENGTH = 115;
	public static final int PROVIDER_FAILURE_ON_ADD = 116;
	public static final int PROVIDER_FAILURE_ON_SET = 117;
	public static final int PROVIDER_CNPJ_UNAVAIABLE = 118;
	public static final int CNPJ_UNAVAIABLE = 119;

	public static final int MIN_NAME_LENGTH = 3;
	public static final int MAX_NAME_LENGTH = 48;
	public static final int CNPJ_LENGTH = 14;
	public static final int CNPJ_MASKED_LENGTH = 18;
	public static final int MIN_COMPANY_NAME_LENGTH = 6;
	public static final int MAX_COMPANY_NAME_LENGTH = 72;
	public static final int MIN_FANTASY_NAME_LENGTH = 6;
	public static final int MAX_FANTASY_NAME_LENGTH = 64;
	public static final int MIN_SPOKESMAN_LENGTH = MIN_NAME_LENGTH;
	public static final int MAX_SPOKESMAN_LENGTH = MAX_NAME_LENGTH;
	public static final int MAX_SITE_URLLEN = 128;

	public static final String REGEX_OPTIONAL = "|^$";
	public static final String REGEX_URL = "((http|https)\\:\\/\\/)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&z\\/=]*)?";
	public static final String REGEX_ALPHA_NUMERIC_UNREQUIRED = "[a-zA-Z0-9 ]*";
	public static final String REGEX_ALPHA_NUMERIC_REQUIRED = "[a-zA-Z0-9 ]+";
	public static final String REGEX_CPF = "[0-9]{14}|[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}";
}
