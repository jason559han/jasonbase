package com.jason.base.exception;

/**
 * dao层访问让事务回滚的异常
 * @author jason558han
 * @date 2020年03月01日
 */
public class TransactionalException extends RuntimeException {

	private static final long serialVersionUID = -5386743057926242826L;

	public TransactionalException() {
		super();
	}
	public TransactionalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public TransactionalException(String message, Throwable cause) {
		super(message, cause);
	}
	public TransactionalException(String message) {
		super(message);
	}
	public TransactionalException(Throwable cause) {
		super(cause);
	}
}
