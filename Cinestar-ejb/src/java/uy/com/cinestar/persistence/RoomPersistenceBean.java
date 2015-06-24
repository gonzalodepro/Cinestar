package uy.com.cinestar.persistence;

import com.sun.xml.ws.rx.rm.runtime.sequence.persistent.PersistenceException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.cinestar.entities.Room;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.exceptions.NoDataException;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class RoomPersistenceBean {

    @PersistenceContext
    EntityManager em;

    public void addRoom(Room room) throws CinestarException {
        try {
            em.persist(room);
        } catch (javax.persistence.EntityExistsException ex) {
            throw new EntityExistsException("Error! Ya existe una sala con ese id en la base de datos.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos de "
                    + "la sala. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al intentar persistir "
                    + "la sala. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public Room getRoom(Long id) throws CinestarException {
        try {
            Room room = em.find(Room.class, id);
            if (room == null) {
                throw new NoDataException("Error! No existe una sala en el sistema con ese id.", null);
            } else {
                return room;
            }
        } catch (CinestarException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al intentar recuperar la sala. "
                    + "Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

}
