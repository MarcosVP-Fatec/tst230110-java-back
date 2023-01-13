package br.com.marcosvp.tst230110javaback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.marcosvp.tst230110javaback.model.PessoaModel;

/**
 * Repositório Pessoa
 * @author Marcos Vinicio Pereira
 */
public interface PessoaRepository extends CrudRepository<PessoaModel, Long>{
	
    @Query("select P from PessoaModel P order by upper(P.nome), id")
    public List<PessoaModel> findAll();
    
    @Query("select P from PessoaModel P where upper(P.nome) like '%?1%'")
    public PessoaModel findByNome(String nome);

}
