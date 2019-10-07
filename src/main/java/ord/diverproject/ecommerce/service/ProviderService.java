package ord.diverproject.ecommerce.service;

import ord.diverproject.ecommerce.model.Provider;

public interface ProviderService
{
	public Provider add(Provider provider);
	public Provider set(Provider provider);
	public Provider get(long idProvider);
	public Iterable<Provider> getAll();
	public Iterable<Provider> getByCnpj(String cnpj);
	public Iterable<Provider> getByCompanyName(String companyName);
	public Iterable<Provider> getByFantasyName(String fantasyName);
}
