
package uy.com.cinestar.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.Room;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.persistence.RoomPersistenceBean;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class RoomBean {

    @EJB
    private RoomPersistenceBean persistence;

    public void addRoom(Room room) throws CinestarException {
        persistence.addRoom(room);
    }

}
