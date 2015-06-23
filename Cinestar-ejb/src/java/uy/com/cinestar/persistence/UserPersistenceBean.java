
package uy.com.cinestar.persistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import uy.com.cinestar.domain.User;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.exceptions.NoDataException;

@Stateless
@LocalBean
public class UserPersistenceBean {

    @PersistenceContext
    EntityManager em;

    public void addUser(User user) throws CinestarException {
        try {
            em.persist(user);
        } catch (javax.persistence.EntityExistsException ex) {
            throw new EntityExistsException("Error! Ya existe un usuario con ese id en la base de datos.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos del usuario. "
                    + "Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al persistir los datos del usuario. "
                    + "Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public List<User> getAllUsers() throws CinestarException {
        try {
            Query query = em.createQuery("SELECT u from User as u");
            return query.getResultList();
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener los "
                    + "datos de los usuarios. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener los "
                    + "datos de los usuarios. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public User getUser(Long id) throws CinestarException {
        try {
            Query query = em.createQuery("SELECT u from User as u WHERE u.id=:id");
            query.setParameter("id", id);
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException ex) {
            throw new NoDataException("Error! No existe un usuario con el id: " + id + " en el sistema.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener los datos del usuario. "
                    + "Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener los "
                    + "datos del usuario. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

}
