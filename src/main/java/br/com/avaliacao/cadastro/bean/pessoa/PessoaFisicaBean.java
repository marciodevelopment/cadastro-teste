package br.com.avaliacao.cadastro.bean.pessoa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.avaliacao.cadastro.entity.pessoa.PessoaFisicaEntity;
import br.com.avaliacao.cadastro.repository.pessoa.PessoaFisicaRepository;

@ViewScoped
@Named
public class PessoaFisicaBean implements Serializable {
	private static final long serialVersionUID = -3917563651871015903L;
	
	@Inject
	private FacesContext faceContext;
	
	@Inject
	private PessoaFisicaRepository pessoaFisicaRepository;
	private PessoaFisicaEntity pessoaFisica;
	
	@PostConstruct
	public void postConstruct() {
		pessoaFisica = pessoaFisicaRepository.findBy(1).get();
	}

	public void clickExcluir() {
		pessoaFisicaRepository.remove(pessoaFisica);	
	}
	
	public void clickSalvar() {
		pessoaFisicaRepository.save(pessoaFisica);
	}
	
	public void clickVoltar() {
		//faceContext.
	}
	
	public void clickNovo() {
		this.pessoaFisica = new PessoaFisicaEntity();
	}
	
	public PessoaFisicaEntity getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaEntity pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
}
