package br.com.avaliacao.cadastro.web.bean.pessoa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;

import br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto;
import br.com.avaliacao.cadastro.repository.pessoa.PessoaRepository;
import br.com.avaliacao.cadastro.web.bean.pessoa.model.PessoaSelecaoLazyDataModel;
import br.com.avaliacao.cadastro.web.util.RedirectUtil;

@ViewScoped
@Named
public class PessoaSelecaoBean implements Serializable {
	private static final long serialVersionUID = -2630840278938888678L;
	public static final String URL = "/cadastro/pages/pessoa-selecao.xhtml";
	@Inject
	private PessoaRepository pessoaRepository;
	
	private LazyDataModel<PessoaSelecaoDto> pessoasSelecao;

	@PostConstruct
	public void postConstruct() {
		pessoasSelecao = new PessoaSelecaoLazyDataModel(pessoaRepository);
	}

	public void clickSelecaoPessoa(PessoaSelecaoDto pessoaSelecao) {
		if (pessoaSelecao.ehPessoaFisica()) {
			PessoaFisicaBean.navegarParaTela(pessoaSelecao.getId());
			return;
		}
		PessoaJuridicaBean.navegarParaTela(pessoaSelecao.getId());
	}
	
	public void clickNovo() {
		PrimeFaces.current().executeScript("PF('dlgNovaPessoa').show();");
	}
	
	public void clickNovaPessoaJuridica() {
		PessoaJuridicaBean.navegarParaTela();
	}
	
	public void clickNovaPessoaFisica() {
		PessoaFisicaBean.navegarParaTela();
	}
	
	public static void navegarParaTela() {
		RedirectUtil.navigate(URL);
	}
	
	public LazyDataModel<PessoaSelecaoDto> getPessoasSelecao() {
		return pessoasSelecao;
	}

	public void setPessoasSelecao(LazyDataModel<PessoaSelecaoDto> pessoasSelecao) {
		this.pessoasSelecao = pessoasSelecao;
	}

	
}
