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
	public long getIdEnd() 							{ return idEnd;					}
	public void setIdEnd(long idEnd)				{ this.idEnd = idEnd;			}
	public PessoaModel getPessoa() 					{ return pessoa;				}
	public void setPessoa(PessoaModel pessoa) 		{ this.pessoa = pessoa;			}
	public String getLogradouro() 					{ return logradouro;			}
	public void setLogradouro(String logradouro) 	{ this.logradouro = logradouro;	}
	public String getNumero() 						{ return numero;				}
	public void setNumero(String numero) 			{ this.numero = numero;			}
	public String getCep() 							{ return cep;					}
	public void setCep(String cep) 					{ this.cep = cep;				}
	public String getCidade() 						{ return cidade;				}
	public void setCidade(String cidade) 			{ this.cidade = cidade;			}
	public boolean isEnderecoPrincipal() 			{ return enderecoPrincipal;		}
	public void setEnderecoPrincipal(boolean enderecoPrincipal) { this.enderecoPrincipal = enderecoPrincipal;}
	
}
