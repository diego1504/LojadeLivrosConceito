package org.casadocodigo.loja.controllers;

import org.casadocodigo.loja.daos.ProdutoDAO;
import org.casadocodigo.loja.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutosController {

	@Autowired   //anotacao para injetar o objeto
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/form";
	}
	
	@RequestMapping("/produtos")
//	public String grava(String titulo, String descricao, int paginas) { <- maneira nao elegante
	public String grava(Produto produto) {	
		System.out.println(produto); //importante lembrar que o JAVA ja faz o binding pois as variaveis estao com o mesmo nome do JSP
		produtoDao.gravar(produto);
		
		
		return "produtos/ok";
	}
	
}
