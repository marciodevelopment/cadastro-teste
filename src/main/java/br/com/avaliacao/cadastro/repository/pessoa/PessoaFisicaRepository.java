package br.com.avaliacao.cadastro.repository.pessoa;

import java.io.Serializable;

import javax.transaction.Transactional;

import br.com.avaliacao.cadastro.common.exception.BusinessRunTimeException;
import br.com.avaliacao.cadastro.common.jpa.QueryBuilder;
import br.com.avaliacao.cadastro.common.util.CpfCnpjUtil;
import br.com.avaliacao.cadastro.entity.pessoa.PessoaFisicaEntity;
import br.com.avaliacao.cadastro.repository.AbstractEntityRepository;

public class PessoaFisicaRepository extends AbstractEntityRepository<PessoaFisicaEntity, Integer> implements Serializable {
	private static final long serialVersionUID = -7841578180329457132L;

	@Override
	protected Class<PessoaFisicaEntity> getEntityClass() {
		return PessoaFisicaEntity.class;
	}
	
	@Override
	@Transactional
	public PessoaFisicaEntity save(PessoaFisicaEntity pessoaFisica) {
		if (existePessoaCadastradaComMesmoCpf(pessoaFisica)) {
			throw new BusinessRunTimeException("pessoa.pessoaNaoFoiSalvaJaExisteUmaPessoaCadastradaComCPF", CpfCnpjUtil.formatar(pessoaFisica.getCpf()));
		}
		return super.save(pessoaFisica);
	}

	private boolean existePessoaCadastradaComMesmoCpf(PessoaFisicaEntity pessoaFisica) {
		return pessoaFisica.getId() == null && countPorCpf(pessoaFisica.getCpf()) > 0 ||
				pessoaFisica.getId() != null && countPorCpfExcluidaAPessoa(pessoaFisica) > 0;
	}
	
	public long countPorCpf(String cpf) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder
			.addNamedQuery("PessoaFisica.countPorCpf")
			.addParamenter("cpf", cpf);
		return super.count(queryBuilder);
	}
	
	public long countPorCpfExcluidaAPessoa(PessoaFisicaEntity pessoaFisica) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder
			.addNamedQuery("PessoaFisica.countPorCpfExcluidaAPessoa")
			.addParamenter("cpf", pessoaFisica.getCpf())
			.addParamenter("pessoaFisica", pessoaFisica);
		return super.count(queryBuilder);
	}
	
}
