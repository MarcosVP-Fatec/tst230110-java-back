package br.com.marcosvp.tst230110javaback.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RespostaHttp {
	
	String message;
	String error;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResponseEntity<RespostaHttp> buildError(String msg, HttpStatus status) {
		return new ResponseEntity(new RespostaHttp().setError(msg), status);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ResponseEntity<RespostaHttp> buildMessage(String msg, HttpStatus status) {
		return new ResponseEntity(new RespostaHttp().setMessage(msg), status);
	}
	
	public String getMessage() 						{ return this.message;	  				}
	public RespostaHttp setMessage(String message)	{ this.message = message; return this;  }
	public String getError() 						{ return this.error;	  				}
	public RespostaHttp setError(String error)		{ this.error = error;     return this;  }
	
}
