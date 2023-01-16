package br.com.marcosvp.tst230110javaback.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Entidade Pessoa
 * @author Marcos Vinicio Pereira
 */
@Entity
@Table( name="pessoa"
      , indexes = {@Index(name="pessoa_idx1", columnList="nome", unique = false)})
public class PessoaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", insertable = true, updatable = false)
	private long id;
	
	@Column(name = "nome", length = 60, nullable = false, insertable = true, updatable = true)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false, insertable = true, updatable = true)
	private Date dataNascimento;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.ALL 
			  ,orphanRemoval = true, targetEntity = PessoaEnderecoModel.class)
	private List<PessoaEnderecoModel> enderecos;
	
	public PessoaModel(){}
	public PessoaModel(Long id){
		this.setId(id);
	}

	public long getId() 							   { return id;						}
	public PessoaModel setId(long id) 				   { this.id = id; return this; 	}
	public String getNome() 						   { return nome;					}
	public PessoaModel setNome(String nome) 		   { this.nome = nome; return this; }
	public Date getDataNascimento() 				   { return dataNascimento;			}
	public PessoaModel setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; return this;	}
	public List<PessoaEnderecoModel> getEnderecos()    { return enderecos;				}
	public PessoaModel setEnderecos(List<PessoaEnderecoModel> enderecos) { this.enderecos = enderecos; return this;	}
	
	//Builder
	public static PessoaModel build() {
		return new PessoaModel().setEnderecos(new ArrayList<PessoaEnderecoModel>());
	}
	public PessoaModel addEndereco(PessoaEnderecoModel novoEndereco) {
		this.getEnderecos().add(novoEndereco);
		return this;
	}
	
}
