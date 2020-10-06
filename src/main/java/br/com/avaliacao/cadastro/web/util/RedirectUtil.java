package br.com.avaliacao.cadastro.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.avaliacao.cadastro.common.exception.ApplicationRunTimeException;

public class RedirectUtil {

	private RedirectUtil() {}
	
	public static void navigateWithFlashScopeValue(String url, String flashScopeId, Object object) {
		FacesContext.getCurrentInstance()
	      .getExternalContext()
	      .getFlash().put(flashScopeId, object); 
		navigate(url);
	}
	
	public static void navigate(String url) {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(url + "?faces-redirect=true");
		} catch (Exception e) {
			throw new ApplicationRunTimeException(e);
		}
	}
	
	public static Object getFlashScope(String flashScopeId) {
		return 
				FacesContext.getCurrentInstance()
				.getExternalContext()
				.getFlash().get(flashScopeId);
	}
	
	public static void back() {
		PrimeFaces.current().executeScript("window.history.back();");
	}
}
