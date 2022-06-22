/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/22/2022 12:05 AM
 */
package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.PaymentDAO;
import lk.ijse.hms.entity.Payment;
import lk.ijse.hms.util.FactoryConfiguration;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(Payment entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Payment find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Payment> findAll() throws Exception {
        return null;
    }

    @Override
    public String findLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        SQLQuery sqlQuery = session.createSQLQuery("select paymentId from Payment order by paymentId desc limit 1");
        System.out.println(sqlQuery.getQueryString());
        return String.valueOf(sqlQuery.list());
    }

    @Override
    public List<String> findAllIds() throws Exception {
        return null;
    }
}
