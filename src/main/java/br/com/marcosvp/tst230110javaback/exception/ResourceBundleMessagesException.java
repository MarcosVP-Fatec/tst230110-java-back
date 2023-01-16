package br.com.marcosvp.tst230110javaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceBundleMessagesException extends RuntimeException {

	private static final long serialVersionUID = 1441111861882427542L;

	public ResourceBundleMessagesException(String key){
        super(String.format("Resource Bundle Key Not Foud: %s", key == null ? "key is null" : key));
    }
	
}



