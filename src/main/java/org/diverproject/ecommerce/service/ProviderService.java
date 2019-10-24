package org.diverproject.ecommerce.service;

import org.diverproject.ecommerce.dto.ProviderDTO;

public interface ProviderService
{
	public ProviderDTO add(ProviderDTO provider);
	public ProviderDTO set(ProviderDTO provider);
	public ProviderDTO get(long idProvider);
	public Iterable<ProviderDTO> getAll();
	public Iterable<ProviderDTO> getByCnpj(String cnpj);
	public Iterable<ProviderDTO> getByCompanyName(String companyName);
	public Iterable<ProviderDTO> getByFantasyName(String fantasyName);
}
