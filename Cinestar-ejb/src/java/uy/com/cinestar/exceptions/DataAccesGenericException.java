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
public class DataAccesGenericException extends CinestarException {

    public DataAccesGenericException(String error, Throwable ex) {
        super(error, ExceptionType.DataAccesGeneric, ex);
    }
    
    
}