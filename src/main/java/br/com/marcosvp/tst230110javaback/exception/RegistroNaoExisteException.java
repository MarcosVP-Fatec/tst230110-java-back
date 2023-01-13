package br.com.marcosvp.tst230110javaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegistroNaoExisteException extends RuntimeException {

	private static final long serialVersionUID = -3254104984191589406L;
	
	public RegistroNaoExisteException() {
        super();
    }

	public RegistroNaoExisteException(Long id, String descricao){
        super(String.format("Registro para alteração não existe: id %d - %s", id, descricao));
    }

}
