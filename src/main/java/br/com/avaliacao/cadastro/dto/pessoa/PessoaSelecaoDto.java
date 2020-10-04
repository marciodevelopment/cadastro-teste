package br.com.avaliacao.cadastro.dto.pessoa;

import java.io.Serializable;

public class PessoaSelecaoDto implements Serializable {
	
	private static final long serialVersionUID = 6183425988553831731L;
	private Integer id;
	private String nome;
	private String razaoSocial;
	private String nomeFantasia;
	private String cpf;
	private String cnpj;
	
	public PessoaSelecaoDto() {
		super();
	}
	

	public PessoaSelecaoDto(Integer id, String nome, String razaoSocial, String nomeFantasia, String cpf, String cnpj) {
		super();
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.id = id;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaSelecaoDto other = (PessoaSelecaoDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}
