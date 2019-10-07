package ord.diverproject.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import ord.diverproject.ecommerce.model.Provider;
import ord.diverproject.ecommerce.model.exception.ProviderException;
import ord.diverproject.ecommerce.repository.ProviderRepository;
import ord.diverproject.ecommerce.utils.EcommerceUtils;

@Component("providerService")
public class EcommerceProviderService implements ProviderService
{
	@Autowired
	private ProviderRepository providerRepository;

	@Override
	public Provider add(Provider provider)
	{
		if (!EcommerceUtils.isValidCnpj(provider.getCnpj()))
			throw ProviderException.newCnpjInvalid(provider);

		if (!this.hasCnpjAvaiable(provider.getCnpj()))
			throw ProviderException.newCnpjUnavaiable(provider);

		try {
			return this.providerRepository.save(provider);
		} catch (DataIntegrityViolationException e) {
			throw ProviderException.newFailureOnAdd(e, provider);
		}
	}

	@Override
	public Provider set(Provider provider)
	{
		if (!this.hasCnpjAvaiable(provider))
			throw ProviderException.newCnpjUnavaiable(provider);

		try {
			return this.providerRepository.findById(provider.getId()).map(p -> {

				if (provider.getCnpj() != null && !EcommerceUtils.isValidCnpj(provider.getCnpj()))
					throw ProviderException.newCnpjInvalid(provider);

				if (provider.getCnpj() != null)			p.setCnpj(provider.getCnpj());
				if (provider.getCompanyName() != null)	p.setCompanyName(provider.getCompanyName());
				if (provider.getFantasyName() != null)	p.setFantasyName(provider.getFantasyName());
				if (provider.getSpokesman() != null)	p.setSpokesman(provider.getSpokesman());
				if (provider.isInactive() != null) 		p.setInactive(provider.isInactive());
				if (provider.getSiteUrl() != null)		p.setSiteUrl(provider.getSiteUrl());

				return this.providerRepository.save(p);

			}).orElseGet(() -> {
				throw ProviderException.newNotExists();
			});
		} catch (DataIntegrityViolationException e) {
			throw ProviderException.newFailureOnSet(e, provider);
		}
	}

	@Override
	public Provider get(long idProvider)
	{
		return this.providerRepository.findById(idProvider).orElse(null);
	}

	@Override
	public Iterable<Provider> getByCnpj(String cnpj)
	{
		return this.providerRepository.findByCnpjContaining(cnpj);
	}

	@Override
	public Iterable<Provider> getByCompanyName(String companyName)
	{
		return this.providerRepository.findByCompanyNameContaining(companyName);
	}

	@Override
	public Iterable<Provider> getByFantasyName(String fantasyName)
	{
		return this.providerRepository.findByFantasyNameContaining(fantasyName);
	}

	public boolean hasCnpjAvaiable(String cnpj)
	{
		return !this.providerRepository.existsByCnpj(cnpj);
	}

	public boolean hasCnpjAvaiable(Provider provider)
	{
		return !this.providerRepository.existsByCnpjAndIdNot(provider.getCnpj(), provider.getId());
	}

	@Override
	public Iterable<Provider> getAll()
	{
		Iterable<Provider> providers = this.providerRepository.findAll();

		return providers;
	}
}
