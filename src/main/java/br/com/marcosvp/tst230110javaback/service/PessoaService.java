package br.com.marcosvp.tst230110javaback.service;

import java.util.List;

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
import br.com.marcosvp.tst230110javaback.model.PessoaModel;
import br.com.marcosvp.tst230110javaback.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepo;

	public List<PessoaModel> getAllPessoas(){
		return pessoaRepo.findAll();
	}

	public PessoaModel getPessoaById(Long id) {
		return pessoaRepo.findById(id).get();
	}

	public PessoaModel getPessoaByNome(String nome) {
		return pessoaRepo.findByNome(nome);
	}

    @Transactional
	public ResponseEntity<?> incluir(PessoaModel pessoa
			,UriComponentsBuilder uriComponentsBuilder) {

		try {
			
			if (pessoa != null && pessoa.getId() != 0) {
				pessoaRepo.findById(pessoa.getId()).ifPresent(p -> {
					throw new RegistroJaExisteException(p.getId(), p.getNome());
				});
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

		try {

			if (pessoa == null || pessoa.getId() == 0) {
				throw new AlteracaoSemIdException(pessoa.getNome());
			}
			
			if (!pessoaRepo.findById(pessoa.getId()).isPresent()) {
				throw new RegistroNaoExisteException(pessoa.getId(), pessoa.getNome());
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
