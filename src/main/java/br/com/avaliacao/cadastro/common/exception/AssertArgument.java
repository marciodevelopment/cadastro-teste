package br.com.avaliacao.cadastro.common.exception;

public class AssertArgument {
	private AssertArgument() {}
	
	/**
	 * Se value for false será lancado uma exceção do tipo BusinessRunTimeException com a mensagem do parâmetro message
	 * @param value
	 * @param message
	 */
	public static void isTrue(boolean value, String message) {
		if (!value) {
			throw new BusinessRunTimeException(message);
		}
	}
}
