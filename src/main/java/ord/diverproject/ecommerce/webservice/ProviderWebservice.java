package ord.diverproject.ecommerce.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ord.diverproject.ecommerce.model.Provider;
import ord.diverproject.ecommerce.service.ProviderService;
import ord.diverproject.ecommerce.utils.EcommerceUtils;

@Controller
public class ProviderWebservice
{
	private static final String PATH = "/provider";

	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public ResponseEntity<WebserviceResponse<Provider>> onProviderAdd(@RequestBody Provider provider)
	{
		long start = System.currentTimeMillis();
		provider = this.providerService.add(provider);

		WebserviceResponse<Provider> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Provider '%s' added with successfull", provider.getFantasyName());
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(provider);

		return webserviceResponse.reponseEntityOk();
	}

	@RequestMapping(value = PATH+ "/{idProvider}", method = RequestMethod.PUT)
	public ResponseEntity<WebserviceResponse<Provider>> onProviderSet(@RequestBody Provider provider, @PathVariable long idProvider)
	{
		long start = System.currentTimeMillis();

		provider.setId(idProvider);
		provider = this.providerService.set(provider);

		WebserviceResponse<Provider> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Provider '%s' updated with successfull", provider.getFantasyName());
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(provider);

		return webserviceResponse.reponseEntityOk();
	}

	@RequestMapping(value = PATH+ "/{idProvider}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Provider>> onProviderSet(@PathVariable long idProvider)
	{
		long start = System.currentTimeMillis();
		Provider provider = this.providerService.get(idProvider);

		WebserviceResponse<Provider> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.calculateDuration(start);

		if (provider != null)
		{
			webserviceResponse.setSuccessfully("Provider '%s' getted with successfull", provider.getFantasyName());
			webserviceResponse.setResult(provider);
		}

		else
			webserviceResponse.setFailure("Provider not found");

		return webserviceResponse.reponseEntityOk();
	}

	@RequestMapping(value = PATH, method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<Provider>>> onProviderListAll()
	{
		long start = System.currentTimeMillis();
		Iterable<Provider> providers = this.providerService.getAll();

		WebserviceResponse<Iterable<Provider>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers", EcommerceUtils.iterableSize(providers));
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}

	@RequestMapping(value = PATH+ "/cnpj/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<Provider>>> onProviderSearchByCnpj(@PathVariable String cnpj)
	{
		long start = System.currentTimeMillis();
		Iterable<Provider> providers = this.providerService.getByCnpj(cnpj);

		WebserviceResponse<Iterable<Provider>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers with CNPJ like '%s'", EcommerceUtils.iterableSize(providers), cnpj);
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}

	@RequestMapping(value = PATH+ "/companyName/{companyName}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<Provider>>> onProviderSearchByCompanyName(@PathVariable String companyName)
	{
		long start = System.currentTimeMillis();
		Iterable<Provider> providers = this.providerService.getByCompanyName(companyName);

		WebserviceResponse<Iterable<Provider>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers with company name like '%s'", EcommerceUtils.iterableSize(providers), companyName);
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}

	@RequestMapping(value = PATH+ "/fantasyName/{fantasyName}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<Provider>>> onProviderSearchByFatansyName(@PathVariable String fantasyName)
	{
		long start = System.currentTimeMillis();
		Iterable<Provider> providers = this.providerService.getByFantasyName(fantasyName);

		WebserviceResponse<Iterable<Provider>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers with fantasy name like '%s'", EcommerceUtils.iterableSize(providers), fantasyName);
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}
}
