package org.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.casadocodigo.loja.model.Carrinho;
import org.casadocodigo.loja.model.DadosPagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(PagamentoController.class);

	@Autowired
	private Carrinho carrinho;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {

		return () -> {

			String uri = "https://book-payment.herokuapp.com/payment";

			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				log.info("Pagamento processado com sucesso");
				model.addFlashAttribute("sucesso", response);
				return new ModelAndView("redirect:/produtos");
			} catch (HttpClientErrorException e) {
				log.warn("Pagamento recusado: {}", e.getStatusCode());
				model.addFlashAttribute("sucesso", "Valor Excede o Permitido");
				return new ModelAndView("redirect:/produtos");
			}

		};

	}
}
