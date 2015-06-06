/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.lang.annotation.Target;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class InterceptorBean {

    
    private SistemBean sistem;
    
    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        try{    
            Object[] parameters = ic.getParameters();
            UUID uuidRequest = UUID.fromString(parameters[0].toString());
            if (sistem.IsCorrectToken(uuidRequest)){
                return ic.proceed();
            }else{
                throw new Exception("El usuario no esta loggeado");
            }
        }catch(Exception e){
            throw e;
            //throw new Exception("Para realizar esta accion debe enviar su token en el primer parametro.");
        }
            
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
