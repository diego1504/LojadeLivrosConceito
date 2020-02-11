package org.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("Entrando no mvc com spring");
		return "home"; // nao preciso colocaar o .jsp, pois a classe de configuracao ja tem a opcao de sufixo

	}

}
