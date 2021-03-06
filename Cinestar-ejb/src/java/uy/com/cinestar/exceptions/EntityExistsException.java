package uy.com.cinestar.exceptions;

import uy.com.cinestar.common.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class EntityExistsException extends CinestarException {

    public EntityExistsException(String error, Throwable ex) {
        super(error, ExceptionType.EntityExists, ex);
    }

}
