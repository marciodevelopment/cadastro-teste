package br.com.avaliacao.cadastro.web.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.avaliacao.cadastro.common.util.TelefoneUtil;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter<String> {
	@Override
	public String getAsObject(FacesContext context, UIComponent component, String value) {
		return TelefoneUtil.retirarFormatacao(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, String value) {
		return TelefoneUtil.formatar(value);
	}
	
}
