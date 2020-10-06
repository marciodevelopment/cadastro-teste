package br.com.avaliacao.cadastro.entity.pessoa;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.avaliacao.cadastro.common.constraint.annotation.Telefone;


@NamedQuery(name = "PessoaFisica.countPorCpfExcluidaAPessoa", 
	query = "select count(pf.id) from PessoaFisicaEntity pf where pf.cpf = :cpf and pf <> :pessoaFisica ")
@NamedQuery(name = "PessoaFisica.countPorCpf", query = "select count(pf.id) from PessoaFisicaEntity pf where pf.cpf = :cpf ")
@Entity(name = "PessoaFisicaEntity")
@Table(name = "PessoaFisica")
public class PessoaFisicaEntity extends PessoaEntity implements Serializable {
	private static final long serialVersionUID = 6366814250579344835L;

	@NotBlank(message = "{nome.notblank}")
	@Size(message = "{nome.size}", min = 5, max = 255)
	private String nome;
	
	@NotBlank(message = "{cpf.notblank}")
	@CPF
	private String cpf;
	
	@NotNull(message = "{datanascimento.notnull}")
	@Past(message = "{datanascimento.past}")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "{telefone.notblank}")
	@Telefone 
	private String telefone;
	
	@Size(message = "{email.max}", max = 64)
	@Email(message = "{email.email}")
	private String email;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	
}
