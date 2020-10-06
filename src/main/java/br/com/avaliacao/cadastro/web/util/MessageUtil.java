package br.com.avaliacao.cadastro.web.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class MessageUtil {
	private static final String properties = "application";

	private MessageUtil() {}

	public static String getMessageResource(String key) {
		return getMessageResource(key, new String[0]);
	}

	private static String getMessageResource(String key, String... params) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(properties, locale);
		try {
			String text = bundle.getString(key);
			if (params == null || params.length == 0) {
				return text;
			}	
			MessageFormat mf = new MessageFormat(text, locale);
			return mf.format(params, new StringBuffer(), null).toString();
		} catch (MissingResourceException e) {
			return key;
		}
	}

	public static void infoMessage(String key) {
		String message = getMessageResource(key);
		String title = getMessageResource("mensagem.sucesso");
		FacesContext
		.getCurrentInstance()
		.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
		keepMessages();
	}

	public static void faltalMessage(String key, String[] args) {
		String message = getMessageResource(key, args);
		String title = getMessageResource("mensagem.atencao");
		FacesContext
		.getCurrentInstance()
		.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
	}
	
	public static void faltalMessage(String key) {
		faltalMessage(key,new String[0]);
	}

	private static void keepMessages() {
		FacesContext.getCurrentInstance()
		.getExternalContext()
		.getFlash().setKeepMessages(true);
	}

}
