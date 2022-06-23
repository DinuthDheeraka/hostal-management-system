/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 9:14 PM
 */
package lk.ijse.hms.bo;

import lk.ijse.hms.bo.custom.impl.*;

import java.io.IOException;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static enum BOType{
        STUDENT,ROOM,RESERVATION,PAYMENT,JOIN_QUERY,ROOM_SETTING_BO
    }

    public SuperBO getBO(BOType boType) throws IOException {
        switch (boType){
            case ROOM:return new RoomBOImpl();
            case STUDENT:return new StudentBOImpl();
            case RESERVATION:return new ReserveBOImpl();
            case PAYMENT:return new PaymentBOImpl();
            case JOIN_QUERY:return new JoinQueryBOImpl();
            case ROOM_SETTING_BO:return new RoomSettingBOImpl();
            default:return null;
        }
    }

    public static BOFactory getInstance(){
        return boFactory==null? boFactory = new BOFactory():boFactory;
    }
}
