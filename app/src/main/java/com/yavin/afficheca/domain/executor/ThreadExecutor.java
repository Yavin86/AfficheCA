package com.yavin.afficheca.domain.executor;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link com.yavin.afficheca.domain.interactor.UseCase} out of the UI thread. TODO
 */
public interface ThreadExecutor extends Executor {}
