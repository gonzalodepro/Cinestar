package uy.com.cinestar.sb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import uy.com.cinestar.entities.Complex;
import uy.com.cinestar.entities.Function;
import uy.com.cinestar.entities.Movie;
import uy.com.cinestar.entities.Room;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.common.Enums;

@Singleton
@LocalBean
public class SystemBean {

    @EJB
    private ComplexBean complexBean;

    @EJB
    private RoomBean roomBean;

    @EJB
    private MovieBean movieBean;

    @EJB
    private FunctionBean functionBean;

    @EJB
    private UserBean userBean;

    private final Map<UUID, User> loggedUsers;

    public SystemBean() {
        this.loggedUsers = new HashMap<>();
    }

    public UUID UserLog(User user) throws CinestarException {
        UUID ret;
        List<User> list = userBean.getAllUsers();
        if (list.contains(user)) {
            User realUser = userBean.getUser(user.getNick());
            ret = UUID.randomUUID();
            loggedUsers.put(ret, realUser);
        } else {
            ret = null;
        }
        return ret;
    }

    public User IsCorrectToken(UUID token) {
        if (this.loggedUsers.containsKey(token)) {
            return loggedUsers.get(token);
        } else {
            return null;
        }
    }

    public void LoadDefaultValues() throws Exception {
        try {
            User u1 = new User();
            u1.setNick("usuCli");
            u1.setPassword("passCli");
            u1.setType(Enums.UserType.Client);

            User u2 = new User();
            u2.setNick("usuAdm");
            u2.setPassword("passAdm");
            u2.setType(Enums.UserType.Administrator);

            User u3 = new User();
            u3.setNick("usuSup");
            u3.setPassword("passSup");
            u3.setType(Enums.UserType.Supervisor);

            userBean.addUser(u1);
            userBean.addUser(u2);
            userBean.addUser(u3);

            Movie m1 = new Movie();
            m1.setTitle("Titanic");
            m1.setDurationMin(120);
            m1.setDescription("Pelicula apta para +18");
            movieBean.addMovie(m1);

            Room r1 = new Room();
            r1.setDescription("Sala teatro");
            r1.setNumber(8);
            roomBean.addRoom(r1);

            Room r2 = new Room();
            r2.setNumber(1);
            r2.setDescription("Di Caprio");

            Function func = new Function();
            func.setMovie(m1);
            func.setRoom(r2);
            Date date = new Date();
            func.setStartDate(date);
            func.setPrice(149);
            functionBean.addFunction(func);

            Complex cplx = new Complex();
            cplx.setName("Movie montevideo Shopping");
            cplx.getRooms().add(r1);
            cplx.getRooms().add(r2);
            cplx.addMovieToBillboard(m1);
            cplx.getFunctions().add(func);
            complexBean.addComplex(cplx);

        } catch (Exception ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos "
                    + "por defecto. Intente nuevamente", ex);
        }
    }
}
