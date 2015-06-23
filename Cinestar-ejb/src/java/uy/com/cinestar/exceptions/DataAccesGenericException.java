
package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class DataAccesGenericException extends CinestarException {

    public DataAccesGenericException(String error, Throwable ex) {
        super(error, ExceptionType.DataAccesGeneric, ex);
    }

}
