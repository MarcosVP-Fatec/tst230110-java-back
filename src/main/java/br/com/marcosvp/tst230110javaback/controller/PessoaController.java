package br.com.marcosvp.tst230110javaback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.marcosvp.tst230110javaback.model.PessoaModel;
import br.com.marcosvp.tst230110javaback.service.PessoaService;

/**
 * Controller Pessoa
 * @author Marcos Vinicio Pereira
 */

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService serv;

	@GetMapping
	public List<PessoaModel> getAllPessoas(){
		return serv.getAllPessoas();
	}
	
	@GetMapping("/{id}")
	public PessoaModel getPessoaById(@PathVariable Long id) {
		return serv.getPessoaById(id);
	}
	
	@GetMapping("/{nome}")
	public PessoaModel getPessoaById(@PathVariable String nome) {
		return serv.getPessoaByNome(nome);
	}

	@PostMapping
    public ResponseEntity<?> incluir(@RequestBody PessoaModel pessoa
    		                        ,UriComponentsBuilder uriComponentsBuilder){
		return serv.incluir(pessoa, uriComponentsBuilder);
    }
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody PessoaModel pessoa
			                          ,UriComponentsBuilder uriComponentsBuilder){
		return serv.atualizar(pessoa, uriComponentsBuilder);
	}

	@DeleteMapping("/{id}")
	public void deletaPessoa(@PathVariable Long id) {
		serv.deletaPessoa(id);
	}
	
}
