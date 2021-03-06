package org.casadocodigo.loja.controllers;

import org.casadocodigo.loja.daos.ProdutoDAO;
import org.casadocodigo.loja.model.Carrinho;
import org.casadocodigo.loja.model.CarrinhoItem;
import org.casadocodigo.loja.model.Produto;
import org.casadocodigo.loja.model.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST, 
    proxyMode=ScopedProxyMode.TARGET_CLASS) 
//um controller atende um request e morre, entao faz sentido delimitar o escopo desta maneira tambem
//o proxyMode serve para deixar o spring cuidar se houverem outras classes controller e nao quiser ficar declarando o escopo da requisicao
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private Carrinho carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		
		carrinho.add(carrinhoItem);
		
		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco); 
		
		return carrinhoItem;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("carrinho/itens");
		return modelAndView;
	
	
	}

	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);
		
		return new ModelAndView("redirect:/carrinho");
	}
	
	
}
