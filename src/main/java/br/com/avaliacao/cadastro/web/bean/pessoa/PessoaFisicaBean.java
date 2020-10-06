package br.com.avaliacao.cadastro.web.bean.pessoa;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.avaliacao.cadastro.entity.pessoa.PessoaFisicaEntity;
import br.com.avaliacao.cadastro.repository.pessoa.PessoaFisicaRepository;
import br.com.avaliacao.cadastro.web.util.MessageUtil;
import br.com.avaliacao.cadastro.web.util.RedirectUtil;

@ViewScoped
@Named
public class PessoaFisicaBean implements Serializable {
	private static final long serialVersionUID = -3917563651871015903L;
	public static final String URL = "/cadastro/pages/pessoa-fisica.xhtml";
	private static final String ID_PESSOA_FLASH_SCOPE = "idPessoa";
	
	@Inject
	private PessoaFisicaRepository pessoaFisicaRepository;
	private PessoaFisicaEntity pessoaFisica;
	
	@PostConstruct
	public void postConstruct() {
		pessoaFisica = recuperaPessoa();
	}
	
	private PessoaFisicaEntity recuperaPessoa() {
		Optional<Integer> idPessoa = getIdPessoaFlashScoped();
		if (idPessoa.isEmpty()) {
			return new PessoaFisicaEntity();
		}
		return
			pessoaFisicaRepository.findBy(idPessoa.get())
			.orElse(new PessoaFisicaEntity());
	}
	
	private Optional<Integer> getIdPessoaFlashScoped() {
		Object idPessoa = RedirectUtil.getFlashScope(ID_PESSOA_FLASH_SCOPE);
		if (idPessoa == null)
			return Optional.empty();
		return Optional.of((Integer) idPessoa);
	}

	public void clickExcluir() {
		pessoaFisicaRepository.delete(pessoaFisica);
		MessageUtil.infoMessage("pessoa.pessoaExluidaComSucesso");
		clickVoltar();
	}
	
	public void clickSalvar() {
		pessoaFisicaRepository.save(pessoaFisica);
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
	
	public PessoaFisicaEntity getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaEntity pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Boolean getRenderBotaoNovo() {
		return this.pessoaFisica.getId() != null;
	}

	
}
