/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:48 PM
 */
package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.ReserveDAO;
import lk.ijse.hms.entity.Reserve;

import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {
    @Override
    public boolean add(Reserve entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Reserve entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Reserve find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Reserve> findAll() throws Exception {
        return null;
    }

    @Override
    public String findLastId() throws Exception {
        return null;
    }
}
