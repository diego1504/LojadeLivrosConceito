package org.casadocodigo.loja.daos;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.model.Produto;
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
	
	
	
}
