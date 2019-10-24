package org.diverproject.ecommerce.utils;

import static org.diverproject.ecommerce.Constants.CNPJ_MASKED_LENGTH;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Iterator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;

public class EcommerceUtils
{
	public static final Time currentTime()
	{
		return new Time(System.currentTimeMillis());
	}

	public static Timestamp currentTimestamp()
	{
		return new Timestamp(System.currentTimeMillis());
	}

	public static <T> int iterableSize(Iterable<T> iterable)
	{
		int size = 0;
		Iterator<T> iterator = iterable.iterator();

		while (iterator.hasNext())
		{
			size++;
			iterator.next();
		}

		return size;
	}

	public static boolean isValidCnpj(String cnpj)
	{
		if (cnpj == null || cnpj.trim().isEmpty())
			return false;

		try {

			cnpj = cnpj.replace('.', ' ');
			cnpj = cnpj.replace('/', ' ');
			cnpj = cnpj.replace('-', ' ');
			cnpj = cnpj.replaceAll(" ", "");

			int soma = 0;
			int dig;
			String cnpjCalc = cnpj.substring(0, 12);

			if (cnpj.length() != 14)
				return false;

			char[] chr_cnpj = cnpj.toCharArray();

			for (int i = 0; i < 4; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (6 - (i + 1));

			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
					soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));

			dig = 11 - soma % 11;
			cnpjCalc += dig == 10 || dig == 11 ? "0" : Integer.toString(dig);
			soma = 0;

			for (int i = 0; i < 5; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (7 - (i + 1));

			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
					soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));

			dig = 11 - soma % 11;
			cnpjCalc += dig == 10 || dig == 11 ? "0" : Integer.toString(dig);

			return cnpj.equals(cnpjCalc);

		} catch (Exception e) {
			return false;
		}
	}

	public static @NotBlank @Pattern(regexp = "[0-9]{14}|[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}") String unmaskCnpj(String cnpj)
	{
		return StringUtils.isEmpty(cnpj) ? null : cnpj.length() == CNPJ_MASKED_LENGTH ? cnpj.replaceAll("\\.|\\/|\\-", "") : cnpj;
	}
}
