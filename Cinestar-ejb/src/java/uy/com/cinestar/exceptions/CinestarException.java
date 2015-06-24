package uy.com.cinestar.exceptions;


import uy.com.cinestar.common.Enums.ExceptionType;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Gonza
 */
public class CinestarException extends Exception {

    private static Logger log = Logger.getLogger(CinestarException.class.getName());
    
    private final String error;
    private final ExceptionType type;
    private final Throwable exception;
    private final String stackTrace;
    
    public CinestarException(String error, ExceptionType type, Throwable ex) {
        this.error = error;
        this.type = type;
        this.exception = ex;
        if (this.exception != null) {
            this.stackTrace = Arrays.toString(ex.getStackTrace());
        } else {
            this.stackTrace = "Sin informacion.";
        }
        logException();
    }

    public CinestarException(String error, Throwable ex) {
        this.error = error;
        this.type = ExceptionType.SystemException;
        this.exception = ex;
        if (this.exception != null) {
            this.stackTrace = Arrays.toString(ex.getStackTrace());
        } else {
            this.stackTrace = "Sin informacion.";
        }
        logException();
    }

    private void logException() {
        BasicConfigurator.configure();
        log.error("Exception test!");
    }

    @Override
    public String getMessage() {
        return error;
    }

    public ExceptionType getType() {
        return type;
    }

}
