package org.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.casadocodigo.infra.FileSaver;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//esta classe visa avisar o servidor que o spring ira tomar o controle das requisiçoes
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class, FileSaver.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; //estou avisando ao servidor que o spring toma conta agora a partir do barra depois do casa do codigo
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encondingFilter = new CharacterEncodingFilter();
		encondingFilter.setEncoding("UTF-8");
		return new Filter[] {encondingFilter};
	}

	//configuracao para tratar arquivos
	@Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }


}
