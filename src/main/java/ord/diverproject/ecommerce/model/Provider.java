package ord.diverproject.ecommerce.model;

import static ord.diverproject.ecommerce.Constants.CNPJ_LENGTH;
import static ord.diverproject.ecommerce.Constants.HIBERNATE_TYPE_DATE;
import static ord.diverproject.ecommerce.Constants.HIBERNATE_TYPE_TIMESTAMP;
import static ord.diverproject.ecommerce.Constants.MAX_COMPANY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.MAX_FANTASY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.MAX_SITE_URLLEN;
import static ord.diverproject.ecommerce.Constants.MAX_SPOKESMAN_LENGTH;
import static ord.diverproject.ecommerce.Constants.MIN_COMPANY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.MIN_FANTASY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.PROVIDER_CNPJ_INVALID;
import static ord.diverproject.ecommerce.Constants.PROVIDER_CNPJ_REQUIRED;
import static ord.diverproject.ecommerce.Constants.PROVIDER_COMPANY_NAME_INVALID;
import static ord.diverproject.ecommerce.Constants.PROVIDER_COMPANY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.PROVIDER_COMPANY_NAME_REQUIRED;
import static ord.diverproject.ecommerce.Constants.PROVIDER_FANTASY_NAME_INVALID;
import static ord.diverproject.ecommerce.Constants.PROVIDER_FANTASY_NAME_LENGTH;
import static ord.diverproject.ecommerce.Constants.PROVIDER_FANTASY_NAME_REQUIRED;
import static ord.diverproject.ecommerce.Constants.PROVIDER_SITE_URL_INVALID;
import static ord.diverproject.ecommerce.Constants.PROVIDER_SITE_URL_LENGTH;
import static ord.diverproject.ecommerce.Constants.PROVIDER_SPOKESMAN_INVALID;
import static ord.diverproject.ecommerce.Constants.PROVIDER_SPOKESMAN_LENGTH;
import static ord.diverproject.ecommerce.Constants.REGEX_ALPHA_NUMERIC_REQUIRED;
import static ord.diverproject.ecommerce.Constants.REGEX_ALPHA_NUMERIC_UNREQUIRED;
import static ord.diverproject.ecommerce.Constants.REGEX_CPF;
import static ord.diverproject.ecommerce.Constants.REGEX_OPTIONAL;
import static ord.diverproject.ecommerce.Constants.REGEX_URL;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.diverproject.scarlet.util.StringUtils;

import ord.diverproject.ecommerce.annotation.ConstraintErrorCode;
import ord.diverproject.ecommerce.utils.EcommerceUtils;

@Entity
@Table(name = "providers")
public class Provider implements Serializable
{
	private static final long serialVersionUID = -8084945663696465193L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Pattern(regexp = REGEX_CPF)
	@ConstraintErrorCode(notBlankCode = PROVIDER_CNPJ_REQUIRED, patternCode = PROVIDER_CNPJ_INVALID)
	@Column(length = CNPJ_LENGTH, nullable = false, unique = true)
	private String cnpj;

	@NotBlank
	@Size(min = MIN_COMPANY_NAME_LENGTH, max = MAX_COMPANY_NAME_LENGTH)
	@Pattern(regexp = REGEX_ALPHA_NUMERIC_REQUIRED)
	@ConstraintErrorCode(notBlankCode = PROVIDER_COMPANY_NAME_REQUIRED, sizeCode = PROVIDER_COMPANY_NAME_LENGTH, patternCode = PROVIDER_COMPANY_NAME_INVALID)
	@Column(length = 72, nullable = false)
	private String companyName;

	@NotBlank
	@Size(min = MIN_FANTASY_NAME_LENGTH, max = MAX_FANTASY_NAME_LENGTH)
	@Pattern(regexp = REGEX_ALPHA_NUMERIC_REQUIRED)
	@ConstraintErrorCode(notBlankCode = PROVIDER_FANTASY_NAME_REQUIRED, sizeCode = PROVIDER_FANTASY_NAME_LENGTH, patternCode = PROVIDER_FANTASY_NAME_INVALID)
	@Column(length = 64, nullable = false)
	private String fantasyName;

	@Column(length = 48)
	@Size(min = 0, max = MAX_SPOKESMAN_LENGTH)
	@Pattern(regexp = REGEX_ALPHA_NUMERIC_UNREQUIRED)
	@ConstraintErrorCode(sizeCode = PROVIDER_SPOKESMAN_LENGTH, patternCode = PROVIDER_SPOKESMAN_INVALID)
	private String spokesman;

	@Column(length = 128)
	@Size(min = 0, max = MAX_SITE_URLLEN)
	@Pattern(regexp = REGEX_URL+REGEX_OPTIONAL)
	@ConstraintErrorCode(sizeCode = PROVIDER_SITE_URL_LENGTH, patternCode = PROVIDER_SITE_URL_INVALID)
	private String siteUrl;

	@Column(nullable = false)
	private boolean inactive;

	@Column(updatable = false, nullable = false, columnDefinition = HIBERNATE_TYPE_DATE)
	private Date register;

	@Column(nullable = false, columnDefinition = HIBERNATE_TYPE_TIMESTAMP)
	private Timestamp lastUpdate;

	/* FIXME: direct phone list */
	/* FIXME: contact list */

	public Provider()
	{
		this.setRegister(EcommerceUtils.currentTime());
		this.setLastUpdate(EcommerceUtils.currentTimestamp());
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getCnpj()
	{
		return this.cnpj;
	}

	public void setCnpj(String cnpj)
	{
		this.cnpj = EcommerceUtils.unmaskCnpj(cnpj);
	}

	public String getCompanyName()
	{
		return this.companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getFantasyName()
	{
		return this.fantasyName;
	}

	public void setFantasyName(String fantasyName)
	{
		this.fantasyName = fantasyName;
	}

	public String getSpokesman()
	{
		return this.spokesman;
	}

	public void setSpokesman(String spokesman)
	{
		this.spokesman = spokesman;
	}

	public String getSiteUrl()
	{
		return this.siteUrl;
	}

	public void setSiteUrl(String siteUrl)
	{
		this.siteUrl = siteUrl;
	}

	public Boolean isInactive()
	{
		return this.inactive;
	}

	public void setInactive(Boolean inactive)
	{
		this.inactive = inactive;
	}

	public Date getRegister()
	{
		return this.register;
	}

	public void setRegister(Date register)
	{
		this.register = register;
	}

	public Timestamp getLastUpdate()
	{
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate)
	{
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString()
	{
		return	StringUtils.objectToString(
			"id", this.getId(),
			"cnpj", this.getCnpj(),
			"companyName", this.getCompanyName(),
			"fantasyName", this.getFantasyName(),
			"spokesman", this.getSpokesman(),
			"siteUrl", this.getSiteUrl(),
			"register", this.getRegister(),
			"lastUpdate", this.getLastUpdate()
		);
	}
}
