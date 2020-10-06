package br.com.avaliacao.cadastro.web.exception.handler;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.ProjectStage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

import br.com.avaliacao.cadastro.common.exception.BusinessRunTimeException;
import br.com.avaliacao.cadastro.web.util.MessageUtil;

public class AplicationExceptionHandler extends ExceptionHandlerWrapper {
	private static final Logger LOGGER = Logger.getLogger(AplicationExceptionHandler.class.getName());
	private ExceptionHandler wrapped;

	public AplicationExceptionHandler(ExceptionHandler wrapped) {
		super(wrapped);
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context == null || context.getResponseComplete()) {
			return;
		}

		Iterable<ExceptionQueuedEvent> exceptionQueuedEvents = getUnhandledExceptionQueuedEvents();
		if (exceptionQueuedEvents != null && exceptionQueuedEvents.iterator() != null) {
			Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents = getUnhandledExceptionQueuedEvents().iterator();

			if (unhandledExceptionQueuedEvents.hasNext()) {
				try {
					Throwable throwable = unhandledExceptionQueuedEvents.next().getContext().getException();

					unhandledExceptionQueuedEvents.remove();

					Throwable rootCause = getRootCause(throwable);
					showMessageBusinessException(rootCause);
					// print exception in development stage
					if (context.getApplication().getProjectStage() == ProjectStage.Development) {
						rootCause.printStackTrace();
					}
				}
				catch (Exception ex) {
					LOGGER.log(Level.SEVERE, "Could not handle exception!", ex);
				}
			}

			while (unhandledExceptionQueuedEvents.hasNext()) {
				unhandledExceptionQueuedEvents.next();
				unhandledExceptionQueuedEvents.remove();
			}
		}
	}
	
	private void showMessageBusinessException(Throwable exception) {
		if (exception.getCause() instanceof BusinessRunTimeException) {
			MessageUtil.faltalMessage(((BusinessRunTimeException) exception.getCause()).getMessagekey(), ((BusinessRunTimeException) exception.getCause()).getArgs());
		}
	}
}
