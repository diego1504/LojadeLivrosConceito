package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.casadocodigo.loja.conf.JPAConfiguration;
import org.casadocodigo.loja.daos.ProdutoDAO;
import org.casadocodigo.loja.model.Produto;
import org.casadocodigo.loja.model.TipoPreco;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.confs.DataSourceConfigurationTest;       

@RunWith(SpringJUnit4ClassRunner.class) // anotacao para dizer ao JUNIT que ele deve usar o spring para executar. Precisamos fazer isso pois o JUNIT sozinho é incapaz de conhecer todo o conceito de memoria que existe
@ContextConfiguration(classes = {JPAConfiguration.class,ProdutoDAO.class,DataSourceConfigurationTest.class}) 
@ActiveProfiles("test") // profile para deixar o spring saber qual instancia do banco de dados iremos usar
public class ProdutoDAOTest {
	
	@Autowired
    private ProdutoDAO produtoDao;

	
	
	@Test 
	@Transactional
	public void deeSomarTodosOsPrecosPorTipoLivro() {
	  

	    List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
	    List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

	    livrosImpressos.stream().forEach(produtoDao::gravar);
	    livrosEbook.stream().forEach(produtoDao::gravar);
	
	    BigDecimal valor = produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
	    Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	   
	}
	
	
	
	
	
}