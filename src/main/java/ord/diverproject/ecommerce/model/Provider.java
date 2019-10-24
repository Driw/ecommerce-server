package ord.diverproject.ecommerce.model;

import ord.diverproject.ecommerce.annotation.ConstraintErrorCode;
import ord.diverproject.ecommerce.dto.ProviderDTO;
import ord.diverproject.ecommerce.utils.CopyOf;
import ord.diverproject.ecommerce.utils.EcommerceUtils;
import org.diverproject.scarlet.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import static ord.diverproject.ecommerce.Constants.*;

@Entity
@Table(name = "providers", indexes = {
	@Index(name = "provider_cnpj", columnList = "cnpj", unique = true),
	@Index(name = "provider_fantasy_name", columnList = "fantasyName", unique = false)
})
public class Provider implements Serializable, CopyOf<ProviderDTO>
{
	private static final long serialVersionUID = -8084945663696465193L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Pattern(regexp = REGEX_CPF)
	@ConstraintErrorCode(notBlankCode = PROVIDER_CNPJ_REQUIRED, patternCode = PROVIDER_CNPJ_INVALID)
	@Column(length = CNPJ_LENGTH, nullable = false)
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

	public Provider(ProviderDTO providerDTO)
	{
		this();
		this.copyOf(providerDTO);
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
	public void copyOf(ProviderDTO providerDTO)
	{
		if (providerDTO.getCnpj() != null)			this.setCnpj(providerDTO.getCnpj());
		if (providerDTO.getCompanyName() != null)	this.setCompanyName(providerDTO.getCompanyName());
		if (providerDTO.getFantasyName() != null)	this.setFantasyName(providerDTO.getFantasyName());
		if (providerDTO.getSpokesman() != null)		this.setSpokesman(providerDTO.getSpokesman());
		if (providerDTO.isInactive() != null) 		this.setInactive(providerDTO.isInactive());
		if (providerDTO.getSiteUrl() != null)		this.setSiteUrl(providerDTO.getSiteUrl());
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
