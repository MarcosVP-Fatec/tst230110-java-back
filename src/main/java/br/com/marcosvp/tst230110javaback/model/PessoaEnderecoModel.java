package br.com.marcosvp.tst230110javaback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entidade Endereço da Pessoa
 * @author Marcos Vinicio Pereira
 */

@Entity
@Table( name="pessoa_endereco"
, indexes = {@Index(name="pessoa_endereco_idx1", columnList="id_pessoa,id_end", unique = true)})

public class PessoaEnderecoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_end", nullable = false, insertable = true, updatable = false)
	private long idEnd;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id", foreignKey=@ForeignKey(name="pessoa_endereco_fk1"), nullable = false, insertable = true, updatable = false )
	private PessoaModel pessoa;
	
	@Column(name = "logradouro", length = 60, nullable = false, insertable = true, updatable = true)
	private String logradouro;
	
	@Column(name = "numero"    , length = 10, nullable = false, insertable = true, updatable = true)
	private String numero;
	
	@Column(name = "cep"       , length =  8, nullable = true , insertable = true, updatable = true)
	private String cep;
	
	@Column(name = "cidade"    , length = 60, nullable = false, insertable = true, updatable = true)
	private String cidade;
	
	@Column(name = "is_endereco_principal", nullable = false,  insertable = true, updatable = true)
	private boolean enderecoPrincipal;

	//Getters & Setters
	public long getIdEnd() 									{ return idEnd;									}
	public PessoaEnderecoModel setIdEnd(long idEnd)			{ this.idEnd = idEnd; return this;				}
	public PessoaModel getPessoa() 							{ return pessoa;								}
	public PessoaEnderecoModel setPessoa(PessoaModel pessoa){ this.pessoa = pessoa; return this;			}
	public String getLogradouro() 							{ return logradouro;							}
	public PessoaEnderecoModel setLogradouro(String logradouro){ this.logradouro = logradouro; return this;	}
	public String getNumero() 								{ return numero;								}
	public PessoaEnderecoModel setNumero(String numero) 	{ this.numero = numero;	return this;			}
	public String getCep() 									{ return cep;									}
	public PessoaEnderecoModel setCep(String cep) 			{ this.cep = cep; return this;					}
	public String getCidade() 								{ return cidade;								}
	public PessoaEnderecoModel setCidade(String cidade) 	{ this.cidade = cidade;	return this;			}
	public boolean isEnderecoPrincipal() 			{ return enderecoPrincipal;		}
	public PessoaEnderecoModel setEnderecoPrincipal(boolean enderecoPrincipal) { this.enderecoPrincipal = enderecoPrincipal; return this;}
	
	//Builder
	public static PessoaEnderecoModel build() {
		return new PessoaEnderecoModel();
	}
	
}
