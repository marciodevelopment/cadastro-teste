package br.com.avaliacao.cadastro.bean.pessoa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto;
import br.com.avaliacao.cadastro.repository.pessoa.PessoaJuridicaRepository;
import br.com.avaliacao.cadastro.repository.pessoa.PessoaRepository;

@ViewScoped
@Named
public class PessoaSelecaoBean implements Serializable {
	private static final long serialVersionUID = -2630840278938888678L;
	@Inject
	private PessoaRepository pessoaRepository;

	@Inject
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	@Inject
	private EntityManager em;

	private String teste;
	private LazyDataModel<PessoaSelecaoDto> pessoasSelecao;

	@Transactional
	@PostConstruct
	public void postConstruct() {
		
		
		
		
		//Optional<PessoaEntity> pessoa = pessoaRepository.findBy(18);
		//System.out.println("oi");
		/*

		Query query = em.createQuery(hql.toString());
		 */
		/*
		StringBuilder hql = new StringBuilder();
		hql
		.append("select new br.com.avaliacao.cadastro.dto.pessoa.SelecaoPessoa(p.nome, p.cpf) ")
		.append(" from PessoaEntity p ")
		.append(" where p.cpf = '1'");

		Query query = em.createQuery(hql.toString());
		 */

		//query.setFirstResult(startPosition);
		//query.setMaxResults(maxResult);
		//pessoasSelecao = query.getResultList();

		/*
		StringBuilder hql = new StringBuilder();
		hql
		.append("select new br.com.avaliacao.cadastro.dto.pessoa.SelecaoPessoa() ")
		.append(" from PessoaEntity p ")
		em.createQuery(hql);
		 */

		criarLazyModel();
	}


	public void criarLazyModel() {
		pessoasSelecao = new LazyDataModel<PessoaSelecaoDto>() {
			private static final long serialVersionUID = 6251128895071132188L;
			private List<PessoaSelecaoDto> datasource;

			@Override
			public PessoaSelecaoDto getRowData(String rowKey) {
				return 
						datasource
						.stream()
						.filter(pessoa -> pessoa.getId().toString().equals(rowKey))
						.findFirst()
						.orElse(null);
			}

			@Override
			public Object getRowKey(PessoaSelecaoDto pessoa) {
				return pessoa.getId();
			}

			@Override
			public List<PessoaSelecaoDto> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
				StringBuilder hql = new StringBuilder();
				hql
				.append("select new br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto(p.id, p.nome, p.razaoSocial, p.nomeFantasia, p.cpf, p.cnpj) ")
				.append(" from PessoaEntity p ");
				
				this.setRowCount(1000);
				Query query = em.createQuery(hql.toString());
				query.setFirstResult(first);
				query.setMaxResults(pageSize);
				return query.getResultList();
				
			}
		};
	}

	public String getTeste() {
		return "funcionou";
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}


	public LazyDataModel<PessoaSelecaoDto> getPessoasSelecao() {
		return pessoasSelecao;
	}


	public void setPessoasSelecao(LazyDataModel<PessoaSelecaoDto> pessoasSelecao) {
		this.pessoasSelecao = pessoasSelecao;
	}

}
