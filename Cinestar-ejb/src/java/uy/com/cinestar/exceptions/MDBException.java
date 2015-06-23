/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.exceptions;

import uy.com.cinestar.generics.Enums;

/**
 *
 * @author Gonza
 */
public class MDBException extends CinestarException {

    public MDBException(String error, Throwable oException) {
        super(error, Enums.ExceptionType.MDBException, oException);
    }
    
}
