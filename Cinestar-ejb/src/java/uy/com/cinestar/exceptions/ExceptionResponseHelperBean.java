/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.exceptions;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class ExceptionResponseHelperBean {
    
    
    public Response exceptionResponse( Exception e){
        
        
        return Response.accepted(e.getMessage()).build();
    }
}
