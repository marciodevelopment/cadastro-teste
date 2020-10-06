package br.com.avaliacao.cadastro.entity.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.avaliacao.cadastro.common.exception.BusinessRunTimeException;

class PessoaJuridicaTest {

	@Test
	void naoDeveAdicionarTelefoneInvalido() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		Assertions.assertThrows(BusinessRunTimeException.class, () -> pessoa.adicionarTelefone("1231"));
		
	}
	
	@Test
	void naoDeveAdicionarTelefoneVazio() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		Assertions.assertThrows(BusinessRunTimeException.class, () -> pessoa.adicionarTelefone(""));
	}
	
	@Test
	void naoDeveAdicionarTelefoneNulo() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		Assertions.assertThrows(BusinessRunTimeException.class, () -> pessoa.adicionarTelefone(null));
		
	}
	
	@Test
	void deveAdicionarTelefoneValido() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		pessoa.adicionarTelefone("41996357686");
		int actual = pessoa.getTelefones().size();
		int expected = 1; 
		assertEquals(expected, actual);
	}
	
	@Test
	void naoDeveAdicionarTelefoneJaExistente() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		pessoa.adicionarTelefone("41-996357686");
		pessoa.adicionarTelefone("41996357686");
		int actual = pessoa.getTelefones().size();
		int expected = 1; 
		assertEquals(expected, actual);
	}
	
	@Test
	void deveRemoverTelefone() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		String nrTelefone = "41996357686";
		pessoa.adicionarTelefone(nrTelefone);
		pessoa.removerTelefone(nrTelefone);
		int actual = pessoa.getTelefones().size();
		int expected = 0; 
		assertEquals(expected, actual);
	}
	
	@Test
	void naoDevePermitirManipularTelefones() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		Set<String> telefones = pessoa.getTelefones();
		Assertions.assertThrows(UnsupportedOperationException.class, () -> telefones.clear());
	}

	@Test
	void deveOcorrerErroAoConsistirTelefonesVazios() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		Assertions.assertThrows(BusinessRunTimeException.class, () -> pessoa.consistirTelefonesAntesDeSalvar());
	}
	
	@Test
	void naoDeveOcorrerErroAoConsistirTelefonesVazios() {
		PessoaJuridicaEntity pessoa = new PessoaJuridicaEntity();
		String nrTelefone = "41996357686";
		pessoa.adicionarTelefone(nrTelefone);
		pessoa.consistirTelefonesAntesDeSalvar();
		int expected = 1;
		int actual = pessoa.getTelefones().size();
		assertEquals(expected, actual);
	}
	
}
