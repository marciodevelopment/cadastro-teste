package br.com.avaliacao.cadastro.repository.pessoa;

import java.io.Serializable;
import java.util.List;

import br.com.avaliacao.cadastro.bean.pessoa.PessoaSelecaoBean;
import br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto;
import br.com.avaliacao.cadastro.entity.pessoa.PessoaEntity;
import br.com.avaliacao.cadastro.repository.AbstractEntityRepository;

public class PessoaRepository extends AbstractEntityRepository<PessoaEntity, Integer> implements Serializable {
	private static final long serialVersionUID = -7841578180329457132L;

	@Override
	protected Class<PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}
	
	public List<PessoaSelecaoDto> buscar() {
		return null;
	}

}


