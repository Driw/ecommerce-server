package ord.diverproject.ecommerce.logger;

public interface EcommerceLoggers
{
	public EcommerceLogger get(Class<?> classz);
	public EcommerceLogger get(String name);
	public EcommerceLogger getDefault();
}
