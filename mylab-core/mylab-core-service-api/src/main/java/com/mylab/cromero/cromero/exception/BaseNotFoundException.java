package com.mylab.cromero.cromero.exception;

/**
* <h1>BaseNotFoundException</h1>
* BaseNotFoundException exception service layer
* <p>
* <b>BaseNotFoundException</b> custom service exception
* for sevice layer
*
* @author  Cristian Romero Matesanz
*
* 
*/
public class BaseNotFoundException extends ServiceException {

    private static final long serialVersionUID = 3490164901440350602L;

    public BaseNotFoundException() {
        super();
    }

    public BaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseNotFoundException(String message) {
        super(message);
    }

    public BaseNotFoundException(Throwable cause) {
        super(cause);
    }

}
