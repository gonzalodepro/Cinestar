/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.interceptors;

import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import uy.com.cinestar.beans.SistemBean;
import uy.com.cinestar.domain.User;
import uy.com.cinestar.exceptions.LogginException;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.generics.Enums;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class SupervisorInterceptorBean {

    @EJB
    private SistemBean sistem;

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        try {
            Object[] parameters = ic.getParameters();
            UUID uuidRequest = UUID.fromString(parameters[0].toString());
            User user = sistem.IsCorrectToken(uuidRequest);
            if (user != null) {
                if (user.getType() == Enums.UserType.Supervisor) {
                    return ic.proceed();
                } else {
                    throw new LogginException("El usuario loggeado no tiene permisos para realizar esta accion.", null);
                }
            } else {
                throw new LogginException("El usuario no esta loggeado.", null);
            }
        } catch (Exception ex) {
            throw new ParameterException("Para realizar esta accion debe enviar su token en el primer parametro.", ex);
        }
    }

}
