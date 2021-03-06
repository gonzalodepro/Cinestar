/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Gonza
 */
@javax.ws.rs.ApplicationPath("Cinestar")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(uy.com.cinestar.resources.ComplexResource.class);
        resources.add(uy.com.cinestar.resources.FunctionResource.class);
        resources.add(uy.com.cinestar.resources.GenericResource.class);
        resources.add(uy.com.cinestar.resources.LogResource.class);
        resources.add(uy.com.cinestar.resources.MovieResource.class);
        resources.add(uy.com.cinestar.resources.TicketResource.class);
        resources.add(uy.com.cinestar.resources.UserResource.class);
    }

}
