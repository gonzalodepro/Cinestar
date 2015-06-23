
package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums;

/**
 *
 * @author Gonza
 */
public class EmailException extends CinestarException {

    public EmailException(String error, Throwable ex) {
        super(error, Enums.ExceptionType.Email, ex);
    }

}
