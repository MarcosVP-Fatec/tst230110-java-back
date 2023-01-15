package br.com.marcosvp.tst230110javaback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.marcosvp.tst230110javaback.model.PessoaEnderecoModel;

public interface PessoaEnderecoRepository extends CrudRepository<PessoaEnderecoModel, Long>{

	@Query("select distinct E"
		  +" from PessoaEnderecoModel  E"
		  +" inner join fetch E.pessoa P"
		  +" where E.pessoa.id = ?1"
		  +" order by E.enderecoPrincipal desc"
		  +"		, E.idEnd")
	public Optional<List<PessoaEnderecoModel>> findAllByPessoa(Long idPessoa);
	
	@Query("select distinct E"
		  +" from PessoaEnderecoModel  E"
		  +" inner join fetch E.pessoa P"
		  +" where E.pessoa.id = ?1"
		  +"   AND P.id = ?2")
	public Optional<PessoaEnderecoModel> findById(Long idPessoa1, Long idPessoa2);

	@Modifying
	@Query("update PessoaEnderecoModel E"
		  +" set E.enderecoPrincipal = false"
		  +" where E.pessoa.id = ?1 "
		  +"   and E.idEnd != ?2")
	public void updateAllEnderecoPrincipalNull(Long idPessoa, Long idEnd);
		
}
