
package uy.com.cinestar.interceptors;

import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import uy.com.cinestar.sb.SystemBean;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.LoginException;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.common.Enums;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class SupervisorInterceptorBean {

    @EJB
    private SystemBean system;

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        try {
            Object[] parameters = ic.getParameters();
            UUID uuidRequest = UUID.fromString(parameters[0].toString());
            User user = system.IsCorrectToken(uuidRequest);
            if (user != null) {
                if (user.getType() == Enums.UserType.Supervisor) {
                    return ic.proceed();
                } else {
                    throw new LoginException("El usuario loggeado no tiene permisos para realizar esta accion.", null);
                }
            } else {
                throw new LoginException("El usuario no esta loggeado.", null);
            }
        } catch (Exception ex) {
            throw new ParameterException("Para realizar esta accion debe enviar su token en el primer parametro.", ex);
        }
    }

}
