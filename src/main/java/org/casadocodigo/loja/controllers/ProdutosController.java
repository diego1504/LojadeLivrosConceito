package org.casadocodigo.loja.controllers;

import java.util.List;

import org.casadocodigo.loja.daos.ProdutoDAO;
import org.casadocodigo.loja.model.Produto;
import org.casadocodigo.loja.model.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produtos") //incluindo aqui o /produtos todos os metodos abaixo herdam este cara
public class ProdutosController {

	@Autowired   //anotacao para injetar o objeto
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		// as duas linhas abaixo servem para enviar para o view o objeto tipo preco que é um enum. usamos o objeto modelandview para enviar esta informacao
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST) //aqui a diferença do metodo é o fato de ser invocado como post
//	public String grava(String titulo, String descricao, int paginas) { <- maneira nao elegante
	public String grava(Produto produto) {	
		System.out.println(produto); //importante lembrar que o JAVA ja faz o binding pois as variaveis estao com o mesmo nome do JSP
		produtoDao.gravar(produto);
		
		
		return "produtos/ok";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {	
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}





}
