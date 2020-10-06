package br.com.avaliacao.cadastro.repository.pessoa;

import java.io.Serializable;

import javax.transaction.Transactional;

import br.com.avaliacao.cadastro.common.exception.BusinessRunTimeException;
import br.com.avaliacao.cadastro.common.jpa.QueryBuilder;
import br.com.avaliacao.cadastro.common.util.CpfCnpjUtil;
import br.com.avaliacao.cadastro.entity.pessoa.PessoaJuridicaEntity;
import br.com.avaliacao.cadastro.repository.AbstractEntityRepository;

public class PessoaJuridicaRepository extends AbstractEntityRepository<PessoaJuridicaEntity> implements Serializable {
	private static final long serialVersionUID = -7841578180329457132L;

	@Override
	protected Class<PessoaJuridicaEntity> getEntityClass() {
		return PessoaJuridicaEntity.class;
	}
	
	@Override
	@Transactional
	public PessoaJuridicaEntity save(PessoaJuridicaEntity pessoaJuridica) {
		if (existePessoaCadastradaComMesmoCnpj(pessoaJuridica)) {
			throw new BusinessRunTimeException("pessoa.pessoaNaoFoiSalvaJaExisteUmaPessoaCadastradaComCNPJ", CpfCnpjUtil.formatar(pessoaJuridica.getCnpj()));
		}
		pessoaJuridica.consistirTelefonesAntesDeSalvar();
		return super.save(pessoaJuridica);
	}
	
	private boolean existePessoaCadastradaComMesmoCnpj(PessoaJuridicaEntity pessoaJuridica) {
		return pessoaJuridica.getId() == null && countPorCnpj(pessoaJuridica.getCnpj()) > 0 ||
				pessoaJuridica.getId() != null && countPorCnpjExcluidaAPessoa(pessoaJuridica) > 0;
	}
	
	public long countPorCnpj(String cnpj) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder
			.addNamedQuery("PessoaJuridica.countPorCnpj")
			.addParamenter("cnpj", cnpj);
		return super.count(queryBuilder);
	}
	
	public long countPorCnpjExcluidaAPessoa(PessoaJuridicaEntity pessoaJuridica) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder
			.addNamedQuery("PessoaJuridica.countPorCnpjExcluidaAPessoa")
			.addParamenter("cnpj", pessoaJuridica.getCnpj())
			.addParamenter("pessoaJuridica", pessoaJuridica);
		return super.count(queryBuilder);
	}

	public PessoaJuridicaEntity buscaPessoaComTelefonesPorId(Integer id) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder
			.addNamedQuery("PessoaJuridica.buscaPessoaComTelefonesPorId")
			.addParamenter("id", id);
		return super.getSingleResult(queryBuilder);
	}
	
}
