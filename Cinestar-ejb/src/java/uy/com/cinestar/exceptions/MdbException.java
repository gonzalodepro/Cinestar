
package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums;

/**
 *
 * @author Gonza
 */
public class MdbException extends CinestarException {

    public MdbException(String error, Throwable ex) {
        super(error, Enums.ExceptionType.MDBException, ex);
    }

}
