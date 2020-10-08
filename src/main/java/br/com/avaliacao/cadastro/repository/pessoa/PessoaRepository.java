package br.com.avaliacao.cadastro.repository.pessoa;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.com.avaliacao.cadastro.common.jpa.QueryBuilder;
import br.com.avaliacao.cadastro.common.jpa.QueryFilter;
import br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto;
import br.com.avaliacao.cadastro.entity.pessoa.PessoaEntity;
import br.com.avaliacao.cadastro.repository.AbstractEntityRepository;

public class PessoaRepository extends AbstractEntityRepository<PessoaEntity> implements Serializable {
	private static final long serialVersionUID = -7841578180329457132L;

	@Override
	protected Class<PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	public List<PessoaSelecaoDto> buscarPessoasSelecao(Integer pageNumber, Integer pageSize, Optional<QueryFilter> queryFilter) {
		QueryBuilder query = new QueryBuilder();
		query
		.appendHql("select new br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto(p.id, p.nome, p.razaoSocial, p.nomeFantasia, p.cpf, p.cnpj) ")
		.appendHql(" from PessoaEntity p ")
		.setPageNumber(pageNumber)
		.setPageSize(pageSize);
		return super.searchDto(adicionarFiltrosQueryParaBuscaPessoaSelecao(query, queryFilter));
	}

	public Long countPessoasSelecao(Optional<QueryFilter> queryFilter) {
		QueryBuilder query = new QueryBuilder();
		query
		.appendHql("select count(*)      ")
		.appendHql(" from PessoaEntity p ");
		return super.count(adicionarFiltrosQueryParaBuscaPessoaSelecao(query, queryFilter));
	}

	private QueryBuilder adicionarFiltrosQueryParaBuscaPessoaSelecao(QueryBuilder query, Optional<QueryFilter> queryFilter) {
		if (queryFilter.isEmpty()) {
			return query;
		}	
		query
		.appendHql(queryFilter.get().getClauseWhere("p"))
		.addParameter(queryFilter.get().getParameterName(), queryFilter.get().getValue());
		return query;
	}

}


