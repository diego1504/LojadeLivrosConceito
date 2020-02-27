package org.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.casadocodigo.loja.model.Carrinho;
import org.casadocodigo.loja.model.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/pagamento")
@Controller
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {

	@Autowired
	private Carrinho carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/finalizar" , method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {
	
		return() -> {
		
		String uri = "http://book-payment.herokuapp.com/payment"; 
		
		try {
			String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()),String.class);
			System.out.println(response);
			System.out.println("passei no novo");
			model.addFlashAttribute("sucesso", response);
			//escopo de flash para mandar uma mansagem a mais na tela de listar
			return new ModelAndView("redirect:/produtos");
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			System.out.println(e);
			model.addFlashAttribute("sucesso", "Valor Excede o Permitido");
			return new ModelAndView("redirect:/produtos");
		}
		
	
		};
	
	}
	//Observação: Observe que estamos usando novamente recursos do Java 8. Esta forma de usar lambda nos permite criar um objeto do mesmo tipo esperado pelo retorno do método, evitando que criemos uma classe anônima. Neste caso é perfeitamente aplicável o recurso, por que na interface Callable só há um método, de nome call.
	//O comportamento padrão da nossa aplicação atualmente tem um problema bem comum em diversas aplicações do gênero. Perceba que ao finalizar uma compra a aplicação envia os dados de pagamento para um outro sistema e fica aguardando uma resposta. Enquanto aguarda uma reposta a aplicação simplesmente para. Isto porque ela executa em uma única thread.

//Atualmente como estamos fazendo poucas operações ao finalizar uma compra, isso pode não aparentar um problema, mas em um sistema real será, pois ao finalizar uma compra, geralmente a operação envolve envio de emails, confirmação de pagamento por terceiros e registro da compra. Juntando essa quantidade de atividades a um possível grande número de usuários acessando o sistema, teremos problemas com um sistema lento que pode trazer diversos outros problemas como queda nas vendas por exemplo.
//
//Pensando nisso, podemos fazer uma pequena otimização na funcionalidade de finalização da compra. Faremos com que ao o usuário finalizar uma compra a requisição seja feita de forma assíncrona e que somente este usuário aguarde a resposta do processamento. Desta forma os demais usuários continuam usando a aplicação sem nenhum problema.
//
//Para implementarmos essa modificação, precisamos apenas modificar a assinatura do método finalizar na classe PagamentoController para que retorne um objeto Callable do tipo ModelAndView. Veja como fazemos isso:
}
