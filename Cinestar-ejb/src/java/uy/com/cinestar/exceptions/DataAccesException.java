/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.exceptions;

/**
 *
 * @author Gonza
 */
public class DataAccesException extends Exception {
    private final String error;

    public DataAccesException(String error) {
        this.error = error;
    }
    
    

    @Override
    public String getMessage() {
        return error;
    }
    
}
