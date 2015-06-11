/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import uy.com.cinestar.exceptions.LogginException;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.generics.*;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class AdminInterceptorBean {

    @EJB
    private SistemBean sistem;
    
    @EJB
    private ExceptionHelperBean exceptionBean;
    
    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        try{    
            Object[] parameters = ic.getParameters();
            UUID uuidRequest = UUID.fromString(parameters[0].toString());
            if (sistem.IsCorrectToken(uuidRequest)){
                return ic.proceed();
            }else{
                throw new LogginException("El usuario no esta loggeado como Administrador");
            }
        }catch(Exception e){
            throw new ParameterException("Para realizar esta accion debe enviar su token en el primer parametro.");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
