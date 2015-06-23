
package uy.com.cinestar.exceptions;

import uy.com.cinestar.generics.Enums;

/**
 *
 * @author Gonza
 */
public class EmailException extends CinestarException {

    public EmailException(String error, Throwable ex) {
        super(error, Enums.ExceptionType.Email, ex);
    }

}
