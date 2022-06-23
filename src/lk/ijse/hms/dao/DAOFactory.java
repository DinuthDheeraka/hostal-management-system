/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 9:05 PM
 */
package lk.ijse.hms.dao;

import lk.ijse.hms.dao.custom.impl.*;

import java.io.IOException;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static enum DAOType{
        STUDENT,ROOM,RESERVATION,PAYMENT,JOIN_QUERY,ROOM_SETTING
    }

    public SuperDAO getDAO(DAOType daoType) throws IOException {
        switch (daoType){
            case ROOM:return new RoomDAOImpl();
            case STUDENT:return new StudentDAOImpl();
            case RESERVATION:return new ReserveDAOImpl();
            case PAYMENT:return new PaymentDAOImpl();
            case JOIN_QUERY:return new JoinQueryDAOImpl();
            case ROOM_SETTING:return new RoomSettingDAOImpl();
            default:return null;
        }
    }

    public static DAOFactory getInstance(){
        return daoFactory==null? daoFactory = new DAOFactory():daoFactory;
    }
}
