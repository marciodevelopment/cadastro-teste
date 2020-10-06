package br.com.avaliacao.cadastro.repository.pessoa;

import java.io.Serializable;
import java.util.List;

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

	public List<PessoaSelecaoDto> buscarPessoasSelecao(Integer pageNumber, Integer pageSize, QueryFilter queryFilter) {
		QueryBuilder query = new QueryBuilder();
		query
		.appendHql("select new br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto(p.id, p.nome, p.razaoSocial, p.nomeFantasia, p.cpf, p.cnpj) ")
		.appendHql(" from PessoaEntity p ")
		.setPageNumber(pageNumber)
		.setPageSize(pageSize);
		adicionarFiltrosQueryParaBuscaPessoaSelecao(query, queryFilter);
		return super.searchDto(query);
	}

	public Long countPessoasSelecao(QueryFilter queryFilter) {
		QueryBuilder query = new QueryBuilder();
		query
		.appendHql("select count(*)      ")
		.appendHql(" from PessoaEntity p ");
		adicionarFiltrosQueryParaBuscaPessoaSelecao(query, queryFilter);
		return super.count(query);
	}

	private QueryBuilder adicionarFiltrosQueryParaBuscaPessoaSelecao(QueryBuilder query, QueryFilter queryFilter) {
		if (queryFilter == null) {
			return query;
		}	
		query
		.appendHql(queryFilter.getClauseWhere("p"))
		.addParamenter(queryFilter.getParameterName(), queryFilter.getValue());
		return query;
	}

}


