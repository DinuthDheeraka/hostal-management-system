/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:48 PM
 */
package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.RoomDAO;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    //DI
    //Session session = FactoryConfiguration.getInstance().getSession();
//    Transaction transaction = session.beginTransaction();

    public RoomDAOImpl() throws IOException {
    }

    @Override
    public boolean add(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Room.class, s));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Room> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from Room r");
        return query.list();
    }

    @Override
    public String findLastId() throws Exception {
        return null;
    }
}
