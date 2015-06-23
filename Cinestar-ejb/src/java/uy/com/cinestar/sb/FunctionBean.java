package uy.com.cinestar.sb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.entities.Function;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.persistence.FunctionPersistenceBean;

@Stateless
@LocalBean
public class FunctionBean {

    @EJB
    private FunctionPersistenceBean functionPersistence;

    public List<Function> getFunctions() throws CinestarException {
        return functionPersistence.getAllFunctions();
    }

    public Function getFunction(long id) throws CinestarException {
        return functionPersistence.getFunction(id);
    }

    public List<Function> getComplexFunctions(Long complexId) throws DataAccesGenericException, CinestarException {
        return functionPersistence.getComplexFunction(complexId);
    }
}
