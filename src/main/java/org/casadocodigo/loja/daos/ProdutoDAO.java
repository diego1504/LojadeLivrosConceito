package org.casadocodigo.loja.daos;



import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.casadocodigo.loja.model.Produto;
import org.casadocodigo.loja.model.TipoPreco;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //dizer que esta classe é um repositorio para o spring
@Transactional ////avisar ao spring para tomar conta das transacoes do banco de dados
public class ProdutoDAO {

	
	@PersistenceContext
	private EntityManager manager;
	
	
	public void gravar(Produto produto) {
		manager.persist(produto);
		
		
	}


	public List<Produto> listar() {
		
		return manager.createQuery("Select p from Produto p", Produto.class).getResultList();
	}

	//select seco, usando chave
	//public Produto find(Integer id) {
	//	return manager.find(Produto.class, id);
		
	//}
			
			
	//select seco, usando chave
		public Produto find(Integer id) {
			return manager.createQuery("Select distinct(p) from Produto p join fetch p.precos where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
			
		}
	
		public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco){
		    TypedQuery<BigDecimal> query = manager.createQuery("select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipoPreco", BigDecimal.class);
		    query.setParameter("tipoPreco", tipoPreco);
		    return query.getSingleResult();
		}
		
}
