package ord.diverproject.ecommerce.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan("org.diverproject.ecommerce")
public class DataConfiguration
{
	private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";

	@Bean
	public DataSource newDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/ecommerce?useTimezone=true&serverTimezone=America/Sao_Paulo");
		dataSource.setUsername("ecommerce");
		dataSource.setPassword("ecommerce");

		return dataSource;
	}

	@Bean
	public JpaVendorAdapter newJpaVendorAdapter()
	{
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform(DIALECT);
		adapter.setPrepareConnection(true);

		return adapter;
	}
}
