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
public class LogginException extends CinestarException {

    public LogginException(String error, Throwable ex) {
        super(error, ExceptionType.Loggin, ex);
    }

}
