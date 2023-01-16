package br.com.marcosvp.tst230110javaback.pattern;

import java.util.ResourceBundle;

/**
 * Singleton para carga de menssagens
 * 
 */
public class Mensagem {

    private static Mensagem instance;
    protected ResourceBundle rb;
    
    private Mensagem() {  }

    public static Mensagem getInstance() {
        if (instance == null) instance = new Mensagem();
        return instance;
    }

    public ResourceBundle getResourceBundle() {
        return (rb == null ? (rb = ResourceBundle.getBundle("br.com.marcosvp.tst230110javaback.pattern.messages")) : rb );
    }
    
    public String get(String chave, Object... substituicoes) {
    	chave = getResourceBundle().getString(chave);
    	if (substituicoes != null) {
    		for (Object obj : substituicoes) {
    			chave = String.format(chave, obj);
			}
    	}
    	return chave;
    }
}
