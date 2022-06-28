/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/26/2022 2:36 AM
 */
package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.SystemUserDAO;
import lk.ijse.hms.entity.SystemUser;
import lk.ijse.hms.util.FactoryConfiguration;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class SystemUserDAOImpl implements SystemUserDAO {
    @Override
    public boolean add(SystemUser entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(SystemUser entity) throws Exception {
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
    public SystemUser find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        SystemUser systemUser = session.get(SystemUser.class,s);
        session.close();
        return systemUser;
    }

    @Override
    public List<SystemUser> findAll() throws Exception {
        return null;
    }

    @Override
    public String findLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        SQLQuery sqlQuery = session.createSQLQuery("select userId from SystemUser order by userId desc limit 1");
        return String.valueOf(sqlQuery.list());
    }

    @Override
    public List<String> findAllIds() throws Exception {
        return null;
    }

    @Override
    public boolean findUserByUserNameAndPassword(String userName, String password) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from SystemUser su where su.password = :pw AND su.userName = :un");
        query.setParameter("pw",password);
        query.setParameter("un",userName);
        return query.list().size()>0;
    }

    @Override
    public SystemUser getSystemUserByUserNameAndPassword(String userName, String password) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from SystemUser su where su.password = :pw AND su.userName = :un");
        query.setParameter("pw",password);
        query.setParameter("un",userName);
        return (SystemUser) query.list().get(0);
    }
}
