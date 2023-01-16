package br.com.marcosvp.tst230110javaback.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.marcosvp.tst230110javaback.model.PessoaModel;
import br.com.marcosvp.tst230110javaback.model.TestePessoaModel;
import br.com.marcosvp.tst230110javaback.pattern.Mensagem;
import br.com.marcosvp.tst230110javaback.repository.PessoaRepository;

@SpringBootTest
@Transactional
@Rollback
public class TestePessoaService {
	
	@Autowired
	PessoaService pessoaServ;
	
	@Autowired
	PessoaRepository pessoaRepo;
	
	private final String pre = "Pessoa Service: ";
	private final Mensagem msg = Mensagem.getInstance();
	private final String MENSAGEM_PESSOA_ID_NAO_INFORMADO = "pessoa.identificador.nao.informado";
	private final String MENSAGEM_EXCLUSAO_JAH_FOI = "pessoa.exclusao.jah.foi.anteriormente";
	private final String MENSAGEM_EXCLUSAO_SUCESSO = "pessoa.exclusao.sucesso";
	
	@Test
    void testeExclusaoSemId() throws ParseException {

		// Id não informado
		assertTrue(msg.getResourceBundle().containsKey(MENSAGEM_PESSOA_ID_NAO_INFORMADO),pre+"msg="+MENSAGEM_PESSOA_ID_NAO_INFORMADO);
		
		ResponseEntity<?> re = pessoaServ.deletaPessoa(0L);
		RespostaHttp body = ( (RespostaHttp) re.getBody() );
		
		assertEquals( body.getClass() , RespostaHttp.class , pre+"Retorno do DEL (0) não foi RespostaHttp.class");
		
		assertEquals( msg.get(MENSAGEM_PESSOA_ID_NAO_INFORMADO)
				    , body.getError()
				    , pre+"Id da pessoa não informado");
		
		assertEquals(re.getStatusCode(), HttpStatus.CONFLICT, pre+"StatusCode 1");
		
		// Pessoa já excluída anteriormente
		Long idJahExcluido = 99999999L;
		while (true) {
			if (!pessoaRepo.existsById(idJahExcluido)) { break; }
			idJahExcluido++;
		}
		
		re = pessoaServ.deletaPessoa(idJahExcluido);
		body = ( (RespostaHttp) re.getBody() );
		
		assertEquals( body.getClass() , RespostaHttp.class , pre+"Retorno do DEL (-1) não foi RespostaHttp.class");
		
		assertEquals( msg.get(MENSAGEM_EXCLUSAO_JAH_FOI,idJahExcluido)
				    , body.getMessage()
				    , pre+"Pessoa já excluída anteriormente");
		
		assertEquals(re.getStatusCode(), HttpStatus.OK, pre+"StatusCode 2");
		
		//Exclusão bem sucedida
		PessoaModel nova = new TestePessoaModel().geraPessoaAleatoria();
		pessoaRepo.save(nova);
		re = pessoaServ.deletaPessoa(nova.getId());
		body = ( (RespostaHttp) re.getBody() );
		assertEquals( body.getClass() , RespostaHttp.class , pre+"Retorno do DEL nova não foi RespostaHttp.class");
		
		assertEquals( msg.get(MENSAGEM_EXCLUSAO_SUCESSO,nova.getId())
				    , body.getMessage()
				    , pre+"Exclusão realizada");
		
		assertEquals(re.getStatusCode(), HttpStatus.OK, pre+"StatusCode 3");
		
	}

}
