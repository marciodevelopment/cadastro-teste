package br.com.avaliacao.cadastro.web.bean.pessoa;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.avaliacao.cadastro.entity.pessoa.PessoaJuridicaEntity;
import br.com.avaliacao.cadastro.repository.pessoa.PessoaJuridicaRepository;
import br.com.avaliacao.cadastro.web.util.MessageUtil;
import br.com.avaliacao.cadastro.web.util.RedirectUtil;

@ViewScoped
@Named
public class PessoaJuridicaBean implements Serializable {
	private static final long serialVersionUID = -3917563651871015903L;
	public static final String URL = "/cadastro/pages/pessoa-juridica.xhtml";
	private static final String ID_PESSOA_FLASH_SCOPE = "idPessoa";
	
	@Inject
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	private PessoaJuridicaEntity pessoaJuridica;
	private String nrTelefone;
	
	@PostConstruct
	public void postConstruct() {
		pessoaJuridica = recuperaPessoa();
	}
	
	private PessoaJuridicaEntity recuperaPessoa() {
		Optional<Integer> idPessoa = getIdPessoaFlashScoped();
		if (idPessoa.isEmpty()) {
			return new PessoaJuridicaEntity();
		}
		return pessoaJuridicaRepository
					.buscaPessoaComTelefonesPorId(idPessoa.get());
	}

	private Optional<Integer> getIdPessoaFlashScoped() {
		Object idPessoa = RedirectUtil.getFlashScope(ID_PESSOA_FLASH_SCOPE);
		if (idPessoa == null)
			return Optional.empty();
		return Optional.of((Integer) idPessoa);
	}
	
	public void clickExcluir() {
		pessoaJuridicaRepository.delete(pessoaJuridica);
		MessageUtil.infoMessage("pessoa.pessoaExluidaComSucesso");
		clickVoltar();
	}
	
	public void clickSalvar() {
		pessoaJuridicaRepository.save(pessoaJuridica);
		MessageUtil.infoMessage("pessoa.pessoaSalvaComSucesso");
		clickVoltar();
	}
	
	public static void clickVoltar() {
		PessoaSelecaoBean.navegarParaTela();
	}
	
	public static void navegarParaTela(Integer idPessoaParaEdicao) {
		RedirectUtil.navigateWithFlashScopeValue(URL, ID_PESSOA_FLASH_SCOPE, idPessoaParaEdicao);
	}
	
	public static void navegarParaTela() {
		RedirectUtil.navigate(URL);
	}
	
	public void adicionarTelefone() {
		if (StringUtils.isBlank(nrTelefone))
			return;
		pessoaJuridica.adicionarTelefone(nrTelefone);
		nrTelefone = null;
	}
	
	public void clickExcluirTelefone(String nrTelefone) {
		if (StringUtils.isBlank(nrTelefone))
			return;
		pessoaJuridica.removerTelefone(nrTelefone);
	}
	
	
	public PessoaJuridicaEntity getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridicaEntity pessoa) {
		this.pessoaJuridica = pessoa;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Boolean getRenderBotaoNovo() {
		return this.pessoaJuridica.getId() != null;
	}
	
}
