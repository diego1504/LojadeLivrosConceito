package org.casadocodigo.loja.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component //o componente precisa ser declarado para que eu possa fazer o autowired depois 
@Scope(value=WebApplicationContext.SCOPE_SESSION ) //teremos uma instancia de escopo para cada sessao de usuario
public class Carrinho {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		
		itens.put(item, getQuantidade(item) + 1);

	}

	private int getQuantidade(CarrinhoItem item) {
		// se nao existir voltar zero para ser adicionado
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
			
		}
		return itens.get(item);
	}
	
	
	public int getQuantidade () {
		return itens.values().stream().reduce(0,(proximo, acumulador) -> proximo + acumulador);
		
	}
	
}