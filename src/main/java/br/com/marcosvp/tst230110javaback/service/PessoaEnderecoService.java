package br.com.marcosvp.tst230110javaback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.marcosvp.tst230110javaback.exception.AlteracaoSemIdException;
import br.com.marcosvp.tst230110javaback.exception.RegistroJaExisteException;
import br.com.marcosvp.tst230110javaback.exception.RegistroNaoExisteException;
import br.com.marcosvp.tst230110javaback.model.PessoaEnderecoModel;
import br.com.marcosvp.tst230110javaback.model.PessoaModel;
import br.com.marcosvp.tst230110javaback.repository.PessoaEnderecoRepository;
import br.com.marcosvp.tst230110javaback.repository.PessoaRepository;

@Service
public class PessoaEnderecoService {

	@Autowired
	PessoaEnderecoRepository pessoaEndRepo;

	@Autowired
	PessoaRepository pessoaRepo;

	public List<PessoaEnderecoModel> getAllByPessoa(Long idPessoa){
		Optional<List<PessoaEnderecoModel>> enderecos = pessoaEndRepo.findAllByPessoa(idPessoa);
		if (enderecos.isPresent()) {
			return enderecos.get();
		}
		return new ArrayList<PessoaEnderecoModel>();
	}

	@Transactional
	public ResponseEntity<?> incluir(PessoaEnderecoModel endereco){

		if (endereco != null && endereco.getIdEnd() != 0) {
			pessoaEndRepo.findById(endereco.getIdEnd()).ifPresent(p -> {
				throw new RegistroJaExisteException(p.getIdEnd());
			});
		}

		try {

			endereco.setPessoa(new PessoaModel(endereco.getPessoa().getId()));
			if (endereco.isEnderecoPrincipal()) {
				pessoaEndRepo.updateAllEnderecoPrincipalNull(endereco.getPessoa().getId(),0L);
			}
			PessoaEnderecoModel pessoaEndResp = pessoaEndRepo.save(endereco);
			return new ResponseEntity<PessoaEnderecoModel>(pessoaEndResp, HttpStatus.CREATED);

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(e.getMessage());

		}

	}

	@Transactional
	public ResponseEntity<?> alterar(PessoaEnderecoModel endereco){

		if (endereco == null || endereco.getIdEnd() == 0) {
			throw new AlteracaoSemIdException(endereco.getLogradouro()==null?"<não informado>":endereco.getLogradouro());
		}

		if (!pessoaEndRepo.findById(endereco.getIdEnd()).isPresent()) {
			throw new RegistroNaoExisteException(endereco.getIdEnd(), endereco.getLogradouro()==null?"<não informado>":endereco.getLogradouro());
		}

		endereco.setPessoa(new PessoaModel(endereco.getPessoa().getId()));

		try {

			if (endereco.isEnderecoPrincipal()) {
				pessoaEndRepo.updateAllEnderecoPrincipalNull(endereco.getPessoa().getId(),endereco.getIdEnd());
			}
			PessoaEnderecoModel pessoaEndResp = pessoaEndRepo.save(endereco);
			return new ResponseEntity<PessoaEnderecoModel>(pessoaEndResp, HttpStatus.OK);

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(e.getMessage());

		}

	}

	public ResponseEntity<?> deletaEnderecoPessoa(Long idEnd) {
		
		if (idEnd == 0) {
			return RespostaHttp.buildError("Identificador do endereço não informado!", HttpStatus.CONFLICT);
		}
		
		Optional<PessoaEnderecoModel> endDel = pessoaEndRepo.findById(idEnd);
		if (!endDel.isPresent()) {
			return RespostaHttp.buildMessage("Este endereço já foi excluído anteriormente!", HttpStatus.OK);
		}
		
		try {
			
			pessoaEndRepo.deleteById(idEnd);
			return RespostaHttp.buildMessage("Endereço excluído com sucesso!", HttpStatus.OK);

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(e.getMessage());

		}
	}
}
