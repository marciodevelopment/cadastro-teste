package br.com.avaliacao.cadastro.entity.pessoa;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.avaliacao.cadastro.common.exception.AssertArgument;
import br.com.avaliacao.cadastro.common.util.TelefoneUtil;


@NamedQuery(name = "PessoaJuridica.countPorCnpjExcluidaAPessoa", 
	query = "select count(pj.id) from PessoaJuridicaEntity pj where pj.cnpj = :cnpj and pj <> :pessoaJuridicaExcluidaDaPesquisa ")
@NamedQuery(name = "PessoaJuridica.countPorCnpj", 
	query = "select count(pj.id) from PessoaJuridicaEntity pj where pj.cnpj = :cnpj ")
@NamedQuery(name = "PessoaJuridica.buscaPessoaComTelefonesPorId", 
	query = "select pj from PessoaJuridicaEntity pj left join fetch pj.telefones where pj.id = :id ")
@Table(name = "PessoaJuridica")
@Entity(name = "PessoaJuridicaEntity")
public class PessoaJuridicaEntity extends PessoaEntity implements Serializable {
		private static final long serialVersionUID = 4943703513851668953L;
	
	@NotBlank(message = "{razaosocial.notblank}")
	@Size(message = "{razaosocial.size}", min = 5, max = 255)
	private String razaoSocial;
	
	@NotBlank(message = "{nomefantasia.notblank}")
	@Size(message = "{nomefantasia.size}", min = 5, max = 255)
	private String nomeFantasia;
	
	@NotBlank(message = "{cnpj.notblank}")
	@CNPJ
	private String cnpj;
	
	@NotEmpty(message = "{telefones.empty}")
	@ElementCollection(fetch = FetchType.LAZY, targetClass = String.class)
	@CollectionTable(name="PessoaJuridicaTelefone", joinColumns = @JoinColumn(name = "idPessoaJuridica"))
	@Column(name="telefone")
	private Set<String> telefones;
	
	@URL
	@Size(message = "site", max = 255)
	private String site;
	
	public boolean adicionarTelefone(String nrTelefone) {
		AssertArgument.isTrue(TelefoneUtil.numeroTelefoneValido(nrTelefone), "pessoaJuridica.numeroDeTelefoneInvalido");
		if (telefones == null)
			telefones = new HashSet<>();
		return telefones.add(TelefoneUtil.retirarFormatacao(nrTelefone));
	}
	
	public void consistirTelefonesAntesDeSalvar() {
		AssertArgument.isTrue(!this.telefones.isEmpty(), "pessoaJuridica.telefones.notempty");
	}
	
	public boolean removerTelefone(String nrTelefone) {
		return this.telefones.remove(nrTelefone);
	}
	
	public Set<String> getTelefones() {
		if (telefones == null) {
			return  Collections.unmodifiableSet(new HashSet<>());
		}
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

