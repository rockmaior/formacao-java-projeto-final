package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","field.required");
		Usuario usuario = (Usuario) target;
		if (usuario.getPassword().length() <= 5) {
			errors.rejectValue ("password", "field.tamanho");
		}
		
		if (!usuario.getPassword().equals(usuario.getRetypePassword())){
			errors.rejectValue("password", "diferentPassword", "Confirmação da senha incorreta");
		}

	}

}
