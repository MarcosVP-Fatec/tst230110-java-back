package br.com.marcosvp.tst230110javaback.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.marcosvp.tst230110javaback.repository.PessoaRepository;
import br.com.marcosvp.tst230110javaback.service.PessoaService;

@SpringBootTest
@Transactional
@Rollback
public class TestePessoaModel {
	
	@Autowired
	PessoaRepository pessoaRepo;
	
	@Autowired
	PessoaService pessoaServ;
	
	@SuppressWarnings("deprecation")
	@Test
    void testeIncluirPessoa() throws ParseException {

		final String pre = "Incluir Pessoa: ";
		final String NOME1 = "Nonono Nonono";
		PessoaModel pessoa = PessoaModel.build()
				.setId(0L)
    			.setNome(NOME1)
    			.setDataNascimento(new Date(2001,5,1));
    	
		final String LOGRADOURO1 = "Rua Nonono";
		final String NUMERO1 = "777";
		final String CIDADE1 = "São Paulo";
		final String CEP1 = "12000000";
		pessoa.addEndereco(PessoaEnderecoModel.build()
				.setPessoa(pessoa)
				.setIdEnd(0L)
    			.setLogradouro(LOGRADOURO1)
    			.setNumero(NUMERO1)
    			.setCidade(CIDADE1)
    			.setCep(CEP1))
    		  .addEndereco(PessoaEnderecoModel.build()
 				.setPessoa(pessoa)
				.setIdEnd(0L)
    			.setLogradouro("Rua Teste Teste")
    			.setNumero("888")
    			.setCidade("São José dos Campos")
    			.setCep("12221111")
    			.setEnderecoPrincipal(true));
		
		pessoa = pessoaRepo.save(pessoa);
		PessoaEnderecoModel endTeste = pessoa.getEnderecos().get(0);
		
		Optional<PessoaModel> novo = pessoaRepo.findById(pessoa.getId());
		
		assertTrue(novo.isPresent(),pre+"Foi criada?");
		assertFalse(novo.get().getId()==0L,pre+"Novo id");
		assertEquals(novo.get().getNome(),NOME1,pre+"Mesmo nome?");
		assertNotNull(novo.get().getDataNascimento(),pre+"Data de Nascimento");
		assertEquals(novo.get().getEnderecos().size(),2,pre+"2 endereços criados");
		assertNotEquals(novo.get(), new PessoaModel(novo.get().getId()),pre+"Pessoa criada diferente de outro objeto mesmo id.");
		
		PessoaEnderecoModel novoEnd = novo.get().getEnderecos().get(0);
		
		assertEquals(novoEnd,endTeste,pre+" PessoaEnderecoModel" );
		assertNotNull(novoEnd.getIdEnd(),pre+"Endereço Id");
		assertEquals(novoEnd.getPessoa(),pessoa,pre+"Endereço PessoaModel" );
		assertEquals(novoEnd.getLogradouro(),LOGRADOURO1,pre+"Endereço Logradouro" );
		assertEquals(novoEnd.getNumero(),NUMERO1,pre+"Endereço Numero" );
		assertEquals(novoEnd.getCidade(),CIDADE1,pre+"Endereço Cidade" );
		assertEquals(novoEnd.getCep(),CEP1,pre+"Endereço CEP" );
		assertFalse(novoEnd.isEnderecoPrincipal(),pre+"Endereço Principal");
    	
    }
	
	@Test
    void testeExcluirPessoa() {
		PessoaModel nova = this.geraPessoaAleatoria();
		pessoaRepo.delete(nova);
		assertFalse(pessoaRepo.existsById(nova.getId()),"Excluir Pessoa");
	}
	
	@SuppressWarnings("deprecation")
	public PessoaModel geraPessoaAleatoria() {
		return pessoaRepo.save(
			PessoaModel.build()
			           .setId(0L)
			           .setNome("XXXXXXXXXXXXX")
			           .setDataNascimento(new Date(2000,0,1))
		); 
	}
	
}
