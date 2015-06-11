/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.generics;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ws.rs.core.Response;
import uy.com.cinestar.persistence.ErrorLogPersistenceBean;
import uy.com.cinestar.exceptions.*;
/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class ExceptionHelperBean {

    @EJB
    private ErrorLogPersistenceBean errorPersistence;
    
    public Response exceptionResponse( Exception e){
        ErrorLog eL = new ErrorLog(e.getMessage());
        if( e.getClass() == DataAccesException.class ){
            eL.setExceptionType(Enums.ExceptionType.DataAcces);
        }else if (e.getClass() == ParameterException.class){
            eL.setExceptionType(Enums.ExceptionType.Parameter);
        }else{
            eL.setExceptionType(Enums.ExceptionType.Other);
        }
        errorPersistence.addLog(eL);
        return Response.accepted(e.getMessage()).build();
    }
}
