package com.mylab.cromero.repository.exception;

/**
* <h1>ServiceException</h1>
* ServiceException exception service layer 
* <p>
* <b>ServiceException</b> custom abstract service exception
* for sevice layer
*
* @author  Cristian Romero Matesanz
*
* 
*/
public abstract class ServiceException extends RuntimeException {


    private static final long serialVersionUID = 6891262195785173370L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
