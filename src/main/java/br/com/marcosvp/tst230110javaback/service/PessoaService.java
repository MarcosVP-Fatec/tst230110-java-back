package br.com.marcosvp.tst230110javaback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.marcosvp.tst230110javaback.exception.AlteracaoSemIdException;
import br.com.marcosvp.tst230110javaback.exception.RegistroJaExisteException;
import br.com.marcosvp.tst230110javaback.exception.RegistroNaoExisteException;
import br.com.marcosvp.tst230110javaback.model.PessoaEnderecoModel;
import br.com.marcosvp.tst230110javaback.model.PessoaModel;
import br.com.marcosvp.tst230110javaback.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepo;
	
	@Autowired
	PessoaEnderecoService pessoaEndServ;
	
	public List<PessoaModel> getAllPessoas(){
		return pessoaRepo.findAll();
	}

	public PessoaModel getPessoaById(Long id) {
		Optional<PessoaModel> pessoa; 
		if (! (pessoa = pessoaRepo.findById(id)).isPresent()) {
			throw new RegistroNaoExisteException(id);
		}
		return pessoa.get();
	}

	public PessoaModel getPessoaContendoNome(String nome) throws Exception {
		Optional<PessoaModel> pessoa; 
		if (! (pessoa = pessoaRepo.findContentByNome(nome)).isPresent()) {
			throw new RegistroNaoExisteException(nome);
		}
		return pessoa.get();
	}

    @Transactional
	public ResponseEntity<?> incluir(PessoaModel pessoa
			,UriComponentsBuilder uriComponentsBuilder) {

		if (pessoa != null && pessoa.getId() != 0) {
			pessoaRepo.findById(pessoa.getId()).ifPresent(p -> {
				throw new RegistroJaExisteException(p.getId(), p.getNome());
			});
		}

		try {
			
			if (pessoa.getEnderecos() == null) {
				pessoa.setEnderecos(new ArrayList<PessoaEnderecoModel>());
			}
			
			PessoaModel pessoaResp = pessoaRepo.save(pessoa);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setLocation(uriComponentsBuilder.path("/pessoa&id="+pessoa.getId()).build().toUri());
			return new ResponseEntity<PessoaModel>(pessoaResp, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(e.getMessage());

		}
	}

    @Transactional
	public ResponseEntity<?> atualizar(PessoaModel pessoa
							          ,UriComponentsBuilder uriComponentsBuilder) {

		if (pessoa == null || pessoa.getId() == 0) {
			throw new AlteracaoSemIdException(pessoa.getNome());
		}
		
		if (!pessoaRepo.findById(pessoa.getId()).isPresent()) {
			throw new RegistroNaoExisteException(pessoa.getId(), pessoa.getNome());
		}
		
		try {

			if (pessoa.getEnderecos() == null) {
				pessoa.setEnderecos( pessoaEndServ.getAllByPessoa(pessoa.getId()) );
			}

			HttpHeaders responseHeaders = new HttpHeaders();
			PessoaModel pessoaResp = pessoaRepo.save(pessoa);
			responseHeaders.setLocation(uriComponentsBuilder.path("/pessoa&id="+pessoa.getId()).build().toUri());
			return new ResponseEntity<PessoaModel>(pessoaResp, responseHeaders, HttpStatus.NO_CONTENT);

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(e.getMessage());

		}
	}

	public void deletaPessoa(Long id) {
		
		try {
			
			pessoaRepo.deleteById(id);

		} catch (Exception e) {

			ResponseEntity.internalServerError().body(e.getMessage());

		}
	}

}
