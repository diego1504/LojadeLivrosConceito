package org.casadocodigo.loja.conf;

import org.casadocodigo.loja.controllers.HomeController;
import org.casadocodigo.loja.daos.ProdutoDAO;
import org.casadocodigo.loja.model.Carrinho;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//classe de configuração criada para sabermos onde fica o homecontroler

@EnableWebMvc

//@ComponentScan (basePackages = {"org.casadocodigo.loja.controllers"})este jeito nao é recomendado pois se mudar o pacote, temos que voltar aqui

@ComponentScan (basePackageClasses = {HomeController.class,ProdutoDAO.class, Carrinho.class})
public class AppWebConfiguration {
	
	//metodo criado para configurar como serao tratados os resources de pagina
	// pasta WEB-INF é padrao servlet, o servidor sabe que deve esconde-la do usuuario
	@Bean //toda classe gerenciada pelo spring é chamada de bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		//resolver.setExposeContextBeansAsAttributes(true); //faz com que todos os nossos beans fiquem disponiveis no JSTL (nice) 
		resolver.setExposedContextBeanNames("carrinho"); //faz somente o bean descrito na classe
		return resolver;
	
		
	
	}
	
	
	//metodo responsavel pela configuracao das mensagens de erro
	@Bean
	public MessageSource messageSource(){
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("/WEB-INF/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setCacheSeconds(1);
	    return messageSource;
	}	
	
	//metodo para configurar como a aplicacao vai tratar data
	@Bean
	public FormattingConversionService mvcConversionService(){
	    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
	    DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
	    formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	    formatterRegistrar.registerFormatters(conversionService);

	    return conversionService;
	}

	//metodo de configuracao para tratar arquivos multimidia.
	@Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

}
