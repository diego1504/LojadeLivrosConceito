package org.casadocodigo.loja.conf;

import org.casadocodigo.loja.controllers.HomeController;
import org.casadocodigo.loja.daos.ProdutoDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//classe de configuração criada para sabermos onde fica o homecontroler

@EnableWebMvc

//@ComponentScan (basePackages = {"org.casadocodigo.loja.controllers"})este jeito nao é recomendado pois se mudar o pacote, temos que voltar aqui

@ComponentScan (basePackageClasses = {HomeController.class,ProdutoDAO.class})
public class AppWebConfiguration {
	
	//metodo criado para configurar como serao tratados os resources de pagina
	// pasta WEB-INF é padrao servlet, o servidor sabe que deve esconde-la do usuuario
	@Bean //toda classe gerenciada pelo spring é chamada de bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	
	
}
