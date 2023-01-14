package br.com.marcosvp.tst230110javaback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.marcosvp.tst230110javaback.model.PessoaModel;

/**
 * Repositório Pessoa
 * @author Marcos Vinicio Pereira
 */
public interface PessoaRepository extends CrudRepository<PessoaModel, Long>{

	@Query("select P from PessoaModel     P								      "
		  +" left join fetch P.enderecos  E								      "
		  +" order by upper(P.nome), P.id, E.isEnderecoPrincipal desc, E.idEnd")
	public List<PessoaModel> findAll();

	@Query("select P from PessoaModel    P               "
		  +" left join fetch P.enderecos E   		     "
		  +" where P.id = ?1          		  		     "
		  +" order by E.isEnderecoPrincipal desc, E.idEnd")
	public Optional<PessoaModel> findById(Long id);
	
	@Query("select P from PessoaModel    P                     "
		  +" left join fetch P.enderecos E            		   "	
		  +" where upper(P.nome) like upper(concat('%',?1,'%'))"
		  +" order by E.isEnderecoPrincipal desc, E.idEnd      ")
	public PessoaModel findByNome(String nome);

}
