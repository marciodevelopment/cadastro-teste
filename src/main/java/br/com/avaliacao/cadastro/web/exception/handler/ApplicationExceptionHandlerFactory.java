package br.com.avaliacao.cadastro.web.exception.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ApplicationExceptionHandlerFactory extends ExceptionHandlerFactory {

	private final ExceptionHandlerFactory wrapped;

    @SuppressWarnings("deprecation") // the default constructor is deprecated in JSF 2.3
    public ApplicationExceptionHandlerFactory(final ExceptionHandlerFactory wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new AplicationExceptionHandler(wrapped.getExceptionHandler());
    }

    @Override
    public ExceptionHandlerFactory getWrapped() {
        return wrapped;
    }

}
