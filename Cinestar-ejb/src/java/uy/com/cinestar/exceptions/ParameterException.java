

package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class ParameterException extends CinestarException {

    public ParameterException(String error, Throwable ex) {
        super(error, ExceptionType.Parameter, ex);
    }

}
