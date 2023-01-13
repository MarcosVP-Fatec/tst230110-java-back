package br.com.marcosvp.tst230110javaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlteracaoSemIdException extends RuntimeException {
	
	private static final long serialVersionUID = -6363022240967353609L;

	public AlteracaoSemIdException() {
        super();
    }

    public AlteracaoSemIdException(String descricao){
        super(String.format("Tentativa de alterar registro sem identificador: %s", descricao));
    }
	
}
