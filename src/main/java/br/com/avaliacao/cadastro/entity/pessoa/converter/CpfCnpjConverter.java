package br.com.avaliacao.cadastro.entity.pessoa.converter;

import javax.persistence.AttributeConverter;

import br.com.avaliacao.cadastro.common.util.CpfCnpjUtil;

public class CpfCnpjConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String cnpjCpfFormatdo) {
		return CpfCnpjUtil.retirarFormatacao(cnpjCpfFormatdo);
	}

	@Override
	public String convertToEntityAttribute(String cnpjCpfSemFormatacao) {
		return cnpjCpfSemFormatacao;
	}
	
}
