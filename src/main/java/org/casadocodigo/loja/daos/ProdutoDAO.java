package org.casadocodigo.loja.daos;



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
			
			
	
	
	
	
}
