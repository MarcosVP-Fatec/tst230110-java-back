package br.com.marcosvp.tst230110javaback.model;

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

	public long getId() 							   { return id;						}
	public void setId(long id) 						   { this.id = id;					}
	public String getNome() 						   { return nome;					}
	public void setNome(String nome) 				   { this.nome = nome;				}
	public Date getDataNascimento() 				   { return dataNascimento;			}
	public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento;	}
	public List<PessoaEnderecoModel> getEnderecos()    { return enderecos;				}
	public void setEnderecos(List<PessoaEnderecoModel> enderecos) { this.enderecos = enderecos;	}

}
