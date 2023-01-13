package br.com.marcosvp.tst230110javaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RegistroJaExisteException extends RuntimeException {

	private static final long serialVersionUID = 6934906475108624923L;

	public RegistroJaExisteException() {
        super();
    }

    public RegistroJaExisteException(Long id, String descricao){
        super(String.format("Registro já existe: id %d - %s", id, descricao));
    }

}
