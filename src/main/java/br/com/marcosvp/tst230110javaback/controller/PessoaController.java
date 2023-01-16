package br.com.marcosvp.tst230110javaback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.marcosvp.tst230110javaback.model.PessoaEnderecoModel;
import br.com.marcosvp.tst230110javaback.model.PessoaModel;
import br.com.marcosvp.tst230110javaback.service.PessoaEnderecoService;
import br.com.marcosvp.tst230110javaback.service.PessoaService;
import br.com.marcosvp.tst230110javaback.service.RespostaHttp;

/**
 * Controller Pessoa
 * @author Marcos Vinicio Pereira
 */

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService serv;
	
	@Autowired
	PessoaEnderecoService servEnd;

	@GetMapping
	public List<PessoaModel> getAllPessoas(){
		return serv.getAllPessoas();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPessoaById(@PathVariable(value="id") Long id) {
		try {
			return new ResponseEntity<PessoaModel>(serv.getPessoaById(id), HttpStatus.OK);
		} catch (Exception e) {
			return RespostaHttp.buildError(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<?> getPessoaContentByNome(@PathVariable(value="nome") String nome) {
		try {
			return new ResponseEntity<PessoaModel>(serv.getPessoaContendoNome(nome), HttpStatus.OK);
		} catch (Exception e) {
			return RespostaHttp.buildError(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/endereco/{idPessoa}")
	public ResponseEntity<?> getEnderecosPessoa(@PathVariable Long idPessoa){
		try {
			return new ResponseEntity<List<PessoaEnderecoModel>>(servEnd.getAllByPessoa(idPessoa), HttpStatus.OK);
		} catch (Exception e) {
			return RespostaHttp.buildError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
    public ResponseEntity<?> incluir(@RequestBody PessoaModel pessoa
    		                        ,UriComponentsBuilder uriComponentsBuilder){

		try {
			return serv.incluir(pessoa, uriComponentsBuilder);
		} catch (Exception e) {
			return RespostaHttp.buildError(e.getMessage(), HttpStatus.CONFLICT);
		}
		
    }
	
	@PostMapping(value = "/endereco")
    public ResponseEntity<?> incluir(@RequestBody PessoaEnderecoModel endereco){
		return this.upinsert(endereco);
    }

	@PatchMapping(value = "/endereco")
    public ResponseEntity<?> alterar(@RequestBody PessoaEnderecoModel endereco){
		return this.upinsert(endereco);
    }

    private ResponseEntity<?> upinsert(@RequestBody PessoaEnderecoModel endereco){

		try {
			if (endereco.getIdEnd() == 0) {
				return servEnd.incluir(endereco);
			}
			return servEnd.alterar(endereco);
			
		} catch (Exception e) {
			return RespostaHttp.buildError(e.getMessage(), HttpStatus.CONFLICT);
		}
		
    }

	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody PessoaModel pessoa
			                          ,UriComponentsBuilder uriComponentsBuilder){
		try {
			return serv.atualizar(pessoa, uriComponentsBuilder);
		} catch (Exception e) {
			return RespostaHttp.buildError(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaPessoa(@PathVariable Long id) {
		return serv.deletaPessoa(id);
	}
	
	@DeleteMapping("/endereco/{idEnd}")
	public ResponseEntity<?> deletaEnderecoPessoa(@PathVariable Long idEnd) {
		return servEnd.deletaEnderecoPessoa(idEnd);
	}
}
