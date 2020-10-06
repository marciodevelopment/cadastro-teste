package br.com.avaliacao.cadastro.web.bean.pessoa.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import br.com.avaliacao.cadastro.common.jpa.QueryFilter;
import br.com.avaliacao.cadastro.dto.pessoa.PessoaSelecaoDto;
import br.com.avaliacao.cadastro.repository.pessoa.PessoaRepository;
import br.com.avaliacao.cadastro.web.util.MatchModelUtil;

public class PessoaSelecaoLazyDataModel extends LazyDataModel<PessoaSelecaoDto> {
	private static final long serialVersionUID = 6251128895071132188L;
	private List<PessoaSelecaoDto> datasource;
	private PessoaRepository pessoaRepository;
	
	public PessoaSelecaoLazyDataModel(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
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
	public List<PessoaSelecaoDto> load(int pageNumber, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
		QueryFilter queryFilter = criarQueryFilter(filterMeta);
		super.setRowCount(pessoaRepository.countPessoasSelecao(queryFilter).intValue());
		datasource = pessoaRepository.buscarPessoasSelecao(pageNumber, pageSize, queryFilter);
		return datasource;
	}
	
	private QueryFilter criarQueryFilter(Map<String, FilterMeta> filterMeta) {
		Optional<Entry<String, FilterMeta>> filterMetaFiltro = 
				filterMeta
				.entrySet()
				.stream()
				.filter(keyValue -> keyValue.getValue().getFilterValue() != null).findFirst();
		
		if (filterMetaFiltro.isEmpty()) {
			return null;
		}
		
		FilterMeta filterPrime = filterMetaFiltro.get().getValue();
		boolean isCaseSensitive = filterPrime.getFilterField().equals("cpf") || filterPrime.getFilterField().equals("cnpj"); 
		return new QueryFilter(filterPrime.getFilterField(), 
				filterPrime.getFilterValue(),
				MatchModelUtil.getMatchModeQueryType(filterPrime.getFilterMatchMode()), 
				isCaseSensitive);
	}
}
