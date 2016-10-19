package com.s4game.core.data.accessor.exception;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月21日 上午11:37:40
 *
 */

public class CacheException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CacheException() {
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CacheException(Throwable throwable) {
        super(throwable);
    }
	
}
