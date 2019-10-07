package ord.diverproject.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ord.diverproject.ecommerce.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>
{
	public boolean existsByCnpj(String cnpj);
	public boolean existsByCnpjAndIdNot(String cnpj, long id);
	public Iterable<Provider> findByCnpjContaining(String cnpj);
	public Iterable<Provider> findByCompanyNameContaining(String companyName);
	public Iterable<Provider> findByFantasyNameContaining(String fantasyName);
}
