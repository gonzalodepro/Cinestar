/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.exceptions;

import java.util.Arrays;
import uy.com.cinestar.generics.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class CinestarException extends Exception {
    
    private final String error;
    private final ExceptionType type;
    private final Throwable oException; 
    private final String stackTrace;
    
    public CinestarException(String error, ExceptionType type, Throwable oException) {
        //aca loggear
        this.error = error;
        this.type=type;
        this.oException= oException;
        if (this.oException != null)
            this.stackTrace = Arrays.toString(oException.getStackTrace());
        else
            this.stackTrace="Sin informacion.";
    }
    
    public CinestarException(String error, Throwable oException) {
        //aca loggear
        this.error = error;
        this.type=ExceptionType.SystemException;
        this.oException= oException;
        if (this.oException != null)
            this.stackTrace = Arrays.toString(oException.getStackTrace());
        else
            this.stackTrace="Sin informacion.";
    }
    
    @Override
    public String getMessage() {
        return error;
    }

    public ExceptionType getType() {
        return type;
    }
    
    
}
