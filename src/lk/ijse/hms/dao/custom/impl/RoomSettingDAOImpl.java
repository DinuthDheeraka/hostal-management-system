/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/23/2022 6:38 PM
 */
package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.RoomSettingDAO;
import lk.ijse.hms.entity.RoomSetting;
import lk.ijse.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class RoomSettingDAOImpl implements RoomSettingDAO {
    @Override
    public boolean add(RoomSetting entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(RoomSetting entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public RoomSetting find(String s) throws Exception {
        return null;
    }

    @Override
    public List<RoomSetting> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from RoomSetting r");
        return query.list();
    }

    @Override
    public String findLastId() throws Exception {
        return null;
    }

    @Override
    public List<String> findAllIds() throws Exception {
        return null;
    }

    @Override
    public List<String> getRoomCategories() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("select rs.type from RoomSetting rs");
        return query.list();
    }

    @Override
    public int getMaxRoomCount(String roomType) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("select rs.maxCount from RoomSetting rs where rs.type = :roomType");
        query.setParameter("roomType",roomType);
        return (int) query.list().get(0);
    }
}
