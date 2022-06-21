/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/22/2022 12:24 AM
 */
package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.JoinQueryDAO;
import lk.ijse.hms.entity.Custom;
import lk.ijse.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class JoinQueryDAOImpl implements JoinQueryDAO {
    @Override
    public Custom getRoomMonthlyRentalByReservationId(String reservationId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("select r.monthlyRental from Room r inner join Reserve rv on rv.room = r.roomId where rv.reserveId = :reservationId");
        query.setParameter("reservationId",reservationId);
        List list = query.list();

        //Creating Custom Entity
        Custom custom = new Custom();
        custom.setMonthlyRental((double)(list.get(0)));
        return custom;
    }
}
