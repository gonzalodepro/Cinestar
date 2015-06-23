
package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class LogginException extends CinestarException {

    public LogginException(String error, Throwable ex) {
        super(error, ExceptionType.Loggin, ex);
    }

}
