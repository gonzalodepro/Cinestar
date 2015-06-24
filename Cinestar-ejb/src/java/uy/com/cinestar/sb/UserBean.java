
package uy.com.cinestar.sb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.persistence.UserPersistenceBean;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class UserBean {

    @EJB
    private UserPersistenceBean userPersistence;

    public void addUser(User user) throws CinestarException {
        userPersistence.addUser(user);
    }

    public List<User> getAllUsers() throws CinestarException {
        return userPersistence.getAllUsers();
    }
    public User getUser(String nick) throws CinestarException{
        return userPersistence.getUser(nick);
    }
    public User getUser(Long id) throws CinestarException{
        return userPersistence.getUser(id);
    }
}
