package br.com.avaliacao.cadastro.common.exception;

public class AssertArgument {
	private AssertArgument() {}
	
	public static void isTrue(boolean value) {
		if (!value) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Se value for false será lancado uma exceção do tipo IllegalArgumentException com a mensagem do parâmetro message
	 * @param value
	 * @param message
	 */
	public static void isTrue(boolean value, String message) {
		if (!value) {
			throw new IllegalArgumentException(message);
		}
	}
}
