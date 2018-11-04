package tos.service.exception;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

/**
 * Created by Ashot Karakhanyan on 17-12-2013
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }


    public ServiceException(String message, PersistenceException cause) {
        super(message, cause);
    }
}
