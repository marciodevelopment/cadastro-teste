package br.com.avaliacao.cadastro.repository.pessoa;

import java.io.Serializable;

import br.com.avaliacao.cadastro.entity.pessoa.PessoaJuridicaEntity;
import br.com.avaliacao.cadastro.repository.AbstractEntityRepository;

public class PessoaJuridicaRepository extends AbstractEntityRepository<PessoaJuridicaEntity, Integer> implements Serializable {
	private static final long serialVersionUID = -7841578180329457132L;

	@Override
	protected Class<PessoaJuridicaEntity> getEntityClass() {
		return PessoaJuridicaEntity.class;
	}

	
}
