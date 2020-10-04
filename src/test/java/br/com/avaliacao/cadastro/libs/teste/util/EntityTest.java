package br.com.avaliacao.cadastro.libs.teste.util;

import javax.persistence.Id;

public class EntityTest {

	@Id
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
