package org.casadocodigo.loja.controllers;

import org.casadocodigo.infra.FileSaver;
import org.casadocodigo.loja.conf.AppWebConfiguration;
import org.casadocodigo.loja.conf.JPAConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.confs.DataSourceConfigurationTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, FileSaver.class })
@ActiveProfiles("test")
public class ProdutosControllerTest {

	@Autowired //dizer para o spring usar o contexto SpringMVC, lembrar de adicionar a classe ServeletSpringMVC no contexto de configuração acima
	private WebApplicationContext wac; //objeto do spring para simular um contexto web, para ser usado na hora do moc
	
	private MockMvc mockMvc; //O Objeto MockMvc será o objeto que fará as requisições para os controllers da nossa aplicação.Este não cria o objeto Mock, mas facilita essa tarefa através de classes auxiliadoras.
	
	@Before //anotacao que diz que este passo deve ser realizado antes do teste
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); //este construtor sim cria o MOC, atraves da conexão com o objeto wac
	}
	
	
	@Test //caso de teste
	public void deveRetornarParaHomeComOsLivros() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/")) //em se passando /
	            .andExpect(MockMvcResultMatchers.model().attributeExists("produtos")) //devo retornar um produto
	            .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp")); //devo direcionar a tela para esta pagina
	}
	
	
	
	
}