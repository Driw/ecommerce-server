package ord.diverproject.ecommerce.dto;

import ord.diverproject.ecommerce.model.Provider;
import ord.diverproject.ecommerce.utils.CopyOf;
import ord.diverproject.ecommerce.utils.EcommerceUtils;
import org.diverproject.scarlet.util.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ProviderDTO implements Serializable, CopyOf<Provider>
{
    private static final long serialVersionUID = -8084945663696465193L;

    private Long id;
    private String cnpj;
    private String companyName;
    private String fantasyName;
    private String spokesman;
    private String siteUrl;
    private boolean inactive;
    private Date register;
    private Timestamp lastUpdate;

    /* FIXME: direct phone list */
    /* FIXME: contact list */

    public ProviderDTO()
    {
    }

    public ProviderDTO(Provider provider)
    {
        this.copyOf(provider);
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
    public void copyOf(Provider provider)
    {
        this.setId(provider.getId());
        this.setCnpj(provider.getCnpj());
        this.setCompanyName(provider.getCompanyName());
        this.setFantasyName(provider.getFantasyName());
        this.setSpokesman(provider.getSpokesman());
        this.setSiteUrl(provider.getSiteUrl());
        this.setRegister(provider.getRegister());
        this.setLastUpdate(provider.getLastUpdate());
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
