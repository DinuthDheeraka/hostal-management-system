/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 9:14 PM
 */
package lk.ijse.hms.bo;

import lk.ijse.hms.bo.custom.impl.ReserveBOImpl;
import lk.ijse.hms.bo.custom.impl.RoomBOImpl;
import lk.ijse.hms.bo.custom.impl.StudentBOImpl;

import java.io.IOException;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static enum BOType{
        STUDENT,ROOM,RESERVATION
    }

    public SuperBO getBO(BOType boType) throws IOException {
        switch (boType){
            case ROOM:return new RoomBOImpl();
            case STUDENT:return new StudentBOImpl();
            case RESERVATION:return new ReserveBOImpl();
            default:return null;
        }
    }

    public static BOFactory getInstance(){
        return boFactory==null? boFactory = new BOFactory():boFactory;
    }
}
