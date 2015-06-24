package uy.com.cinestar.common;

/**
 *
 * @author Gonza
 */
public class Enums {

    public enum UserType {

        Client,
        Administrator,
        Supervisor
    }

    public enum ExceptionType {

        Parameter,
        DataAccesGeneric,
        NoData,
        EntityExists,
        Loggin,
        Email,
        MDBException,
        SystemException
    }

}
