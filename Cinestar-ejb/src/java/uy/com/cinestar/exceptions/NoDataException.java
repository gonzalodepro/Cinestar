
package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums;

/**
 *
 * @author Gonza
 */
public class NoDataException extends CinestarException {

    public NoDataException(String error, Throwable ex) {
        super(error, Enums.ExceptionType.NoData, ex);
    }

}
