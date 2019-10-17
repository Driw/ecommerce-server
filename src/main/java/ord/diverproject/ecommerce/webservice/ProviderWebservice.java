package ord.diverproject.ecommerce.webservice;

import ord.diverproject.ecommerce.dto.ProviderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ord.diverproject.ecommerce.service.ProviderService;
import ord.diverproject.ecommerce.utils.EcommerceUtils;

@Controller
public class ProviderWebservice
{
	private static final String PATH = "/provider";

	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public ResponseEntity<WebserviceResponse<ProviderDTO>> onProviderAdd(@RequestBody ProviderDTO provider)
	{
		long start = System.currentTimeMillis();
		provider = this.providerService.add(provider);

		WebserviceResponse<ProviderDTO> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Provider '%s' added with successfully", provider.getFantasyName());
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(provider);

		return webserviceResponse.reponseEntityOk();
	}

	@RequestMapping(value = PATH+ "/{idProvider}", method = RequestMethod.PUT)
	public ResponseEntity<WebserviceResponse<ProviderDTO>> onProviderSet(@RequestBody ProviderDTO provider, @PathVariable long idProvider)
	{
		long start = System.currentTimeMillis();

		provider.setId(idProvider);
		provider = this.providerService.set(provider);

		WebserviceResponse<ProviderDTO> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Provider '%s' updated with successfully", provider.getFantasyName());
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(provider);

		return webserviceResponse.reponseEntityOk();
	}

	@RequestMapping(value = PATH+ "/{idProvider}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<ProviderDTO>> onProviderSet(@PathVariable long idProvider)
	{
		long start = System.currentTimeMillis();
		ProviderDTO provider = this.providerService.get(idProvider);

		WebserviceResponse<ProviderDTO> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.calculateDuration(start);

		if (provider != null)
		{
			webserviceResponse.setSuccessfully("Provider '%s' successfully obtained", provider.getFantasyName());
			webserviceResponse.setResult(provider);
		}

		else
			webserviceResponse.setFailure("Provider not found");

		return webserviceResponse.reponseEntityOk();
	}

	@RequestMapping(value = PATH, method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<ProviderDTO>>> onProviderListAll()
	{
		long start = System.currentTimeMillis();
		Iterable<ProviderDTO> providers = this.providerService.getAll();

		WebserviceResponse<Iterable<ProviderDTO>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers", EcommerceUtils.iterableSize(providers));
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}

	@RequestMapping(value = PATH+ "/cnpj/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<ProviderDTO>>> onProviderSearchByCnpj(@PathVariable String cnpj)
	{
		long start = System.currentTimeMillis();
		Iterable<ProviderDTO> providers = this.providerService.getByCnpj(cnpj);

		WebserviceResponse<Iterable<ProviderDTO>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers with CNPJ like '%s'", EcommerceUtils.iterableSize(providers), cnpj);
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}

	@RequestMapping(value = PATH+ "/companyName/{companyName}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<ProviderDTO>>> onProviderSearchByCompanyName(@PathVariable String companyName)
	{
		long start = System.currentTimeMillis();
		Iterable<ProviderDTO> providers = this.providerService.getByCompanyName(companyName);

		WebserviceResponse<Iterable<ProviderDTO>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers with company name like '%s'", EcommerceUtils.iterableSize(providers), companyName);
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}

	@RequestMapping(value = PATH+ "/fantasyName/{fantasyName}", method = RequestMethod.GET)
	public ResponseEntity<WebserviceResponse<Iterable<ProviderDTO>>> onProviderSearchByFantasyName(@PathVariable String fantasyName)
	{
		long start = System.currentTimeMillis();
		Iterable<ProviderDTO> providers = this.providerService.getByFantasyName(fantasyName);

		WebserviceResponse<Iterable<ProviderDTO>> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setSuccessfully("Found %d providers with fantasy name like '%s'", EcommerceUtils.iterableSize(providers), fantasyName);
		webserviceResponse.calculateDuration(start);
		webserviceResponse.setResult(providers);

		return webserviceResponse.reponseEntityNotOk();
	}
}
