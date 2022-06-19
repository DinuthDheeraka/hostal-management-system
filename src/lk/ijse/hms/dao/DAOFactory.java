/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 9:05 PM
 */
package lk.ijse.hms.dao;

import lk.ijse.hms.dao.custom.impl.ReserveDAOImpl;
import lk.ijse.hms.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hms.dao.custom.impl.StudentDAOImpl;

import java.io.IOException;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static enum DAOType{
        STUDENT,ROOM,RESERVATION
    }

    public SuperDAO getDAO(DAOType daoType) throws IOException {
        switch (daoType){
            case ROOM:return new RoomDAOImpl();
            case STUDENT:return new StudentDAOImpl();
            case RESERVATION:return new ReserveDAOImpl();
            default:return null;
        }
    }

    public static DAOFactory getInstance(){
        return daoFactory==null? daoFactory = new DAOFactory():daoFactory;
    }
}
