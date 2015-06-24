
package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class LoginException extends CinestarException {

    public LoginException(String error, Throwable ex) {
        super(error, ExceptionType.Loggin, ex);
    }

}
