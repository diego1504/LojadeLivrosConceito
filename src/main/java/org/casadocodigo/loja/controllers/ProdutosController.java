package org.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.casadocodigo.infra.FileSaver;
import org.casadocodigo.loja.daos.ProdutoDAO;
import org.casadocodigo.loja.model.Produto;
import org.casadocodigo.loja.model.TipoPreco;
import org.casadocodigo.valida.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos") //incluindo aqui o /produtos todos os metodos abaixo herdam este cara
public class ProdutosController {

	@Autowired   //anotacao para injetar o objeto
	private ProdutoDAO produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
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
	@CacheEvict(value="produtosHome",allEntries=true) //anotacao para limpar o cache e fazer as consultas irem buscar novamente
	public ModelAndView grava(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes ) { 
		//incluimos o @valid para o spring ja validar os dados de objeto para nos {	
		//o binding result deve ficar depois do produto, para o spring entender
		//inclusao do multipartfile para receber imagens 
		System.out.println(produto); //importante lembrar que o JAVA ja faz o binding pois as variaveis estao com o mesmo nome do JSP
		
		//System.out.println(sumario.getOriginalFilename()); //mostrar nome do arquivo que fizemos upload
		
		if (result.hasErrors()) {
			System.out.println("entrei no erro");
			return form(produto);
		}
		
		
		String path = fileSaver.write("arquivos-sumario", sumario);
	    produto.setSumarioPath(path);

		
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


	@RequestMapping("/detalhe/{id}") //{id} URL amigavel
	public ModelAndView detalhe(@PathVariable("id") Integer id) {	//PathVariable faz o lunk da url amigavel com o metodo
		
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produtos", produto);
		return modelAndView;
	}

	/* metodo removido, iremos fazer um resolver no appwebconfiguration para usar o mesmo metodo do detalge
	@RequestMapping("/{id}") //metodo para retornar json
	@ResponseBody //anotacao para transformar em json usando jackson, que esta na pom.xml
	public Produto detalheJson(@PathVariable("id") Integer id) {	//PathVariable faz o lunk da url amigavel com o metodo
		
		return produtoDao.find(id);
		
	}
	*/

}
