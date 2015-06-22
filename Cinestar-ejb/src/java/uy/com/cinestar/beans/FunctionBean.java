package uy.com.cinestar.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.Function;
import uy.com.cinestar.persistence.FunctionPersistenceBean;

@Stateless
@LocalBean
public class FunctionBean {

    @EJB
    private FunctionPersistenceBean functionPersistence;
    
    
    public List<Function> getFunctions(){
        return functionPersistence.getAllFunctions();
    }
    public Function getFunction(long id){
        return functionPersistence.getFunction(id);
    }
    public List<Function> getComplexFunctions (Long complexId){
        return functionPersistence.getComplexFunction(complexId);
    }
}
