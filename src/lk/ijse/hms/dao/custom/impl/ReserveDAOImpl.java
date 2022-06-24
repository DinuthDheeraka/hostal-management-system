/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:48 PM
 */
package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.ReserveDAO;
import lk.ijse.hms.entity.Reserve;
import lk.ijse.hms.util.FactoryConfiguration;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {
    @Override
    public boolean add(Reserve entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Reserve find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Reserve reserve = session.get(Reserve.class,s);
        session.close();
        return reserve;
    }

    @Override
    public List<Reserve> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from Reserve r");
        return query.list();
    }

    @Override
    public String findLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        SQLQuery sqlQuery = session.createSQLQuery("select reserveId from Reserve order by reserveId desc limit 1");
        System.out.println(sqlQuery.getQueryString());
        return String.valueOf(sqlQuery.list());
    }

    @Override
    public List<String> findAllIds() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("select r.reserveId from Reserve r ").list();
    }
}
