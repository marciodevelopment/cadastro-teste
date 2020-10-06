package br.com.avaliacao.cadastro.common.exception;

public class BusinessRunTimeException extends RuntimeException {
	private static final long serialVersionUID = -4001257734214129519L;
	private final String messagekey;
	private final String[] args;
	
	public BusinessRunTimeException(String messagekey, String... args) { 
		this.messagekey = messagekey;
		this.args = args;
	}

	public String getMessagekey() {
		return messagekey;
	}

	public String[] getArgs() {
		return args;
	}
}
