package br.com.avaliacao.cadastro.entity.pessoa;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.avaliacao.cadastro.common.constraint.annotation.Telefone;
import br.com.avaliacao.cadastro.common.util.CpfCnpjUtil;
import br.com.avaliacao.cadastro.common.util.TelefoneUtil;
import br.com.avaliacao.cadastro.entity.pessoa.converter.CpfCnpjConverter;


@NamedQuery(name = "PessoaFisica.countPorCpfExcluidaAPessoa", 
	query = "select count(pf.id) from PessoaFisicaEntity pf where pf.cpf = :cpf and pf <> :pessoaFisica ")
@NamedQuery(name = "PessoaFisica.countPorCpf", query = "select count(pf.id) from PessoaFisicaEntity pf where pf.cpf = :cpf ")
@Entity(name = "PessoaFisicaEntity")
@Table(name = "PessoaFisica")
public class PessoaFisicaEntity extends PessoaEntity implements Serializable {
	private static final long serialVersionUID = 6366814250579344835L;

	@NotBlank(message = "Nome")
	@Size(message = "Nome", min = 5, max = 255)
	private String nome;
	
	@Convert(converter = CpfCnpjConverter.class)
	@NotBlank(message = "CPF")
	@Size(message = "CPF", min = 11, max = 11)
	@CPF(message = "CPF")
	private String cpf;
	
	@NotNull(message = "Data de nascimento")
	@Past(message = "Data de nascimento")
	private LocalDate dataNascimento;
	
	@Size(message = "Email", min = 10, max = 11)
	@NotBlank(message = "Telefone")
	@Telefone(message = "Telefone")
	private String telefone;
	
	@Size(message = "Email", min = 5, max = 64)
	@Email(message = "Email")
	private String email;
	
	
	
	/**
	 * @deprecated Utilizar os outros construtores que indicam os parâmetros minímos para um objeto válido
	 */
	@Deprecated
	public PessoaFisicaEntity() {}
	
	public PessoaFisicaEntity(@NotEmpty(message = "Nome") @Size(message = "Nome", min = 5, max = 255) String nome,
			@NotEmpty(message = "CPF") @Size(message = "CPF", min = 11, max = 11) @CPF(message = "CPF") String cpf,
			@NotNull(message = "Data de nascimento") @Past(message = "Data de nascimento") LocalDate dataNascimento,
			@Size(message = "Email", min = 10, max = 11) @NotNull(message = "Telefone") String telefone) {
		super();
		this.nome = nome;
		this.cpf = CpfCnpjUtil.retirarFormatacao(cpf);
		this.dataNascimento = dataNascimento;
		this.telefone = TelefoneUtil.retirarFormatacao(telefone);
	}
	
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
