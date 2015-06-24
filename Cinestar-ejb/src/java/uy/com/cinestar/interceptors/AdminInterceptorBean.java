package uy.com.cinestar.interceptors;

import java.util.UUID;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import uy.com.cinestar.sb.SystemBean;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.LoginException;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.common.Enums;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;

@Stateless
@LocalBean
public class AdminInterceptorBean {

    @Inject
    private SystemBean system;

    @Inject
    private ExceptionResponseHelperBean exceptionHelper;

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        try {
            Object[] parameters = ic.getParameters();
            UUID uuidRequest = UUID.fromString(parameters[0].toString());
            User user = system.IsCorrectToken(uuidRequest);
            if (user != null) {
                if (user.getType() == Enums.UserType.Administrator) {
                    return ic.proceed();
                } else {
                    throw new LoginException("El usuario logueado no tiene permisos para realizar esta accion.", null);
                }
            } else {
                throw new LoginException("El usuario no esta logueado. Para realizar esta accion debe "
                        + "ingresar al sistema como Administrador.", null);
            }
        } catch (LoginException ex) {
            return exceptionHelper.exceptionResponse(ex);
        } catch (IllegalArgumentException ex) {
            return exceptionHelper.exceptionResponse(new ParameterException("El token enviado no tiene el formato correcto.", ex));
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(new LoginException("Para realizar esta accion debe enviar su token en el primer parametro.", ex));
        }
    }

}
