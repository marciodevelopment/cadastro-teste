package br.com.avaliacao.cadastro.bean.pessoa;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class PessoaSelecaoBean implements Serializable {
	private static final long serialVersionUID = -2630840278938888678L;
	private String teste;

	public String getTeste() {
		return "funcionou";
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}
	
	
}
