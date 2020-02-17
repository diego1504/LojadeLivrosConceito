package org.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.casadocodigo.loja.daos.ProdutoDAO;
import org.casadocodigo.loja.model.Produto;
import org.casadocodigo.loja.model.TipoPreco;
import org.casadocodigo.valida.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos") //incluindo aqui o /produtos todos os metodos abaixo herdam este cara
public class ProdutosController {

	@Autowired   //anotacao para injetar o objeto
	private ProdutoDAO produtoDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto)  { //incluido o objeto produto para que ele seja visivel no JSP usando form
		// as duas linhas abaixo servem para enviar para o view o objeto tipo preco que é um enum. usamos o objeto modelandview para enviar esta informacao
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST) //aqui a diferença do metodo é o fato de ser invocado como post
//	public String grava(String titulo, String descricao, int paginas) { <- maneira nao elegante
	public ModelAndView grava(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes ) { 
		//incluimos o @valid para o spring ja validar os dados de objeto para nos {	
		//o binding result deve ficar depois do produto, para o spring entender
		System.out.println(produto); //importante lembrar que o JAVA ja faz o binding pois as variaveis estao com o mesmo nome do JSP
		
		if (result.hasErrors()) {
			System.out.println("entrei no erro");
			return form(produto);
		}
		
		produtoDao.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso","Produto cadastrado com sucesso!"); //esta linha pendura, este objeto para ser usado na proxima requisicao
		
		//return listar(); //aqui nos chamamos o metodo listar, poré este não é recomendavel pois deixaria aberto o bug do F5
		return new ModelAndView("redirect:produtos");  //always redirect after post
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {	
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}





}
