package br.com.avaliacao.cadastro.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.avaliacao.cadastro.common.util.CpfCnpjUtil;

@FacesConverter("cpfCnpjConverter")
public class CpfCnpjConverter implements Converter<String> {
	@Override
	public String getAsObject(FacesContext context, UIComponent component, String value) {
		return CpfCnpjUtil.retirarFormatacao(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, String value) {
		return CpfCnpjUtil.formatar(value);
	}
	
}
