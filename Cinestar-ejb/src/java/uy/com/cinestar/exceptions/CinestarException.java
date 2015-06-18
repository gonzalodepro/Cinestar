/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.exceptions;

import uy.com.cinestar.generics.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
public class CinestarException extends Exception {
    
    private final String error;
    private final ExceptionType type;
    
    public CinestarException(String error, ExceptionType type) {
        //aca loggear
        this.error = error;
        this.type=type;
    }
    
    @Override
    public String getMessage() {
        return error;
    }
    
    
}
