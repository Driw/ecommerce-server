package org.diverproject.ecommerce.service;

import org.diverproject.ecommerce.dto.ProviderDTO;
import org.diverproject.ecommerce.model.Provider;
import org.diverproject.ecommerce.model.exception.ProviderException;
import org.diverproject.ecommerce.repository.ProviderRepository;
import org.diverproject.ecommerce.utils.EcommerceUtils;
import org.diverproject.ecommerce.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component("providerService")
public class EcommerceProviderService implements ProviderService
{
	@Autowired
	private ProviderRepository providerRepository;

	@Override
	public ProviderDTO add(ProviderDTO providerDTO)
	{
		Provider provider = new Provider(providerDTO);

		if (!EcommerceUtils.isValidCnpj(provider.getCnpj()))
			throw ProviderException.newCnpjInvalid(provider);

		if (!this.hasCnpjAvailable(provider.getCnpj()))
			throw ProviderException.newCnpjUnavaiable(provider);

		try {

			provider = this.providerRepository.save(provider);
			providerDTO.copyOf(provider);

			return providerDTO;

		} catch (DataIntegrityViolationException e) {
			throw ProviderException.newFailureOnAdd(e, provider);
		}
	}

	@Override
	public ProviderDTO set(ProviderDTO providerDTO)
	{
		Provider provider = new Provider(providerDTO);

		if (!this.hasCnpjAvailable(provider))
			throw ProviderException.newCnpjUnavaiable(provider);

		try {

			provider = this.providerRepository.findById(providerDTO.getId()).map(p -> {

				p.copyOf(providerDTO);

				if (providerDTO.getCnpj() != null && !EcommerceUtils.isValidCnpj(providerDTO.getCnpj()))
					throw ProviderException.newCnpjInvalid(p);

				return this.providerRepository.save(p);

			}).orElseGet(() -> {
				throw ProviderException.newNotExists();
			});

			return new ProviderDTO(provider);

		} catch (DataIntegrityViolationException e) {
			throw ProviderException.newFailureOnSet(e, provider);
		}
	}

	@Override
	public ProviderDTO get(long idProvider)
	{
		Provider provider = this.providerRepository.findById(idProvider).orElse(null);

		return new ProviderDTO(provider);
	}

	@Override
	public Iterable<ProviderDTO> getByCnpj(String cnpj)
	{
		List<ProviderDTO> providers = new LinkedList<>();

		for (Provider provider : this.providerRepository.findByCnpjContaining(cnpj))
			providers.add(new ProviderDTO(provider));

		return providers;
	}

	@Override
	public Iterable<ProviderDTO> getByCompanyName(String companyName)
	{
		return ServiceUtils.castIterableToDto(this.providerRepository.findByCompanyNameContaining(companyName), ProviderDTO.class);
	}

	@Override
	public Iterable<ProviderDTO> getByFantasyName(String fantasyName)
	{
		return ServiceUtils.castIterableToDto(this.providerRepository.findByFantasyNameContaining(fantasyName), ProviderDTO.class);
	}

	private boolean hasCnpjAvailable(String cnpj)
	{
		return !this.providerRepository.existsByCnpj(cnpj);
	}

	private boolean hasCnpjAvailable(Provider provider)
	{
		return !this.providerRepository.existsByCnpjAndIdNot(provider.getCnpj(), provider.getId());
	}

	@Override
	public Iterable<ProviderDTO> getAll()
	{
		return ServiceUtils.castIterableToDto(this.providerRepository.findAll(), ProviderDTO.class);
	}
}
