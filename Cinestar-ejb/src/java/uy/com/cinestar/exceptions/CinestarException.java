/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.exceptions;

import java.util.Arrays;
import uy.com.cinestar.common.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class CinestarException extends Exception {

    private final String error;
    private final ExceptionType type;
    private final Throwable exception;
    private final String stackTrace;

    public CinestarException(String error, ExceptionType type, Throwable ex) {
        //aca loggear
        this.error = error;
        this.type = type;
        this.exception = ex;
        if (this.exception != null) {
            this.stackTrace = Arrays.toString(ex.getStackTrace());
        } else {
            this.stackTrace = "Sin informacion.";
        }
    }

    public CinestarException(String error, Throwable ex) {
        //aca loggear
        this.error = error;
        this.type = ExceptionType.SystemException;
        this.exception = ex;
        if (this.exception != null) {
            this.stackTrace = Arrays.toString(ex.getStackTrace());
        } else {
            this.stackTrace = "Sin informacion.";
        }
    }

    @Override
    public String getMessage() {
        return error;
    }

    public ExceptionType getType() {
        return type;
    }

}
