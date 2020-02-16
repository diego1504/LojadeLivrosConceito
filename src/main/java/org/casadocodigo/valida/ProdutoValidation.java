package org.casadocodigo.valida;

import org.casadocodigo.loja.model.Produto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProdutoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Produto.class.isAssignableFrom(clazz); //avisa que o produto deve ser validado por esta classe ProdutoValidation
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
	
		Produto produto = (Produto) target; //preciso fazer este cast para poder usar o objeto produto
		if(produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");
		}
			
		
	}



}
