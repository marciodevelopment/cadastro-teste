package br.com.avaliacao.cadastro.entity.pessoa;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.avaliacao.cadastro.common.exception.AssertArgument;
import br.com.avaliacao.cadastro.common.util.CpfCnpjUtil;
import br.com.avaliacao.cadastro.common.util.TelefoneUtil;

@Entity(name = "PessoaJuridica")
public class PessoaJuridicaEntity extends PessoaEntity implements Serializable {
		private static final long serialVersionUID = 4943703513851668953L;
	
	@NotBlank(message = "Razão Social")
	@Size(message = "Razão Social", min = 5, max = 255)
	private String razaoSocial;
	
	@NotBlank(message = "Nome Fantasia")
	@Size(message = "Nome Fantasia", min = 5, max = 255)
	private String nomeFantasia;
	
	@NotBlank(message = "CNPJ")
	@CNPJ(message = "CNPJ")
	private String cnpj;
	
	@Size(max = 20, min = 1, message = "Telefones")
	@NotEmpty(message = "Telefones")
	@ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
	@CollectionTable(name="PessoaJuridicaTelefone", joinColumns = @JoinColumn(name = "idPessoaJuridica"))
	@Column(name="telefone")
	private Set<String> telefones = new HashSet<>(1);
	
	@URL
	@Size(message = "site", min = 5, max = 255)
	private String site;
	
	public PessoaJuridicaEntity(
			@NotEmpty(message = "Razão Social") @Size(message = "Razão Social", min = 5, max = 255) String razaoSocial,
			@NotEmpty(message = "Nome Fantasia") @Size(message = "Nome Fantasia", min = 5, max = 255) String nomeFantasia,
			@NotEmpty(message = "CNPJ") @CNPJ(message = "CNPJ") String cnpj,
			@Max(value = 20, message = "Telefones") @NotEmpty(message = "Telefones") Set<String> telefones) {
		super();
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = CpfCnpjUtil.retirarFormatacao(cnpj);
		telefones.forEach(nrTelefone -> this.adicionarTelefone(TelefoneUtil.retirarFormatacao(nrTelefone)));
		this.telefones.add("41996357686");
	}
	
	public PessoaJuridicaEntity(
			@NotEmpty(message = "Razão Social") @Size(message = "Razão Social", min = 5, max = 255) String razaoSocial,
			@NotEmpty(message = "Nome Fantasia") @Size(message = "Nome Fantasia", min = 5, max = 255) String nomeFantasia,
			@NotEmpty(message = "CNPJ") @CNPJ(message = "CNPJ") String cnpj,
			@Max(value = 20, message = "Telefones") @NotEmpty(message = "Telefones") String telefone) {
		this(razaoSocial, nomeFantasia, cnpj, new HashSet<>(Arrays.asList(telefone)));
	}
	
	
	/**
	 * Utilizar os outros construtores que indicam os parâmetros minímos para um objeto válido
	 */
	@Deprecated
	public PessoaJuridicaEntity() {}
	
	public boolean adicionarTelefone(String nrTelefone) {
		AssertArgument.isTrue(TelefoneUtil.numeroTelefoneValido(nrTelefone), "Número de telefone inválido");
		return telefones.add(TelefoneUtil.retirarFormatacao(nrTelefone));
	}
	
	
	public boolean removerTelefone(String nrTelefone) {
		return this.telefones.remove(nrTelefone);
	}
	
	public Set<String> getTelefones() {
		return Collections.unmodifiableSet(this.telefones);
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


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}
}
