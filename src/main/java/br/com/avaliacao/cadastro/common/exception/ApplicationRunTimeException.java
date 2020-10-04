package br.com.avaliacao.cadastro.common.exception;

public class ApplicationRunTimeException extends RuntimeException {
	private static final long serialVersionUID = -7968450698416259469L;

	public ApplicationRunTimeException(IllegalAccessException e) {
		super(e);
	}
}
