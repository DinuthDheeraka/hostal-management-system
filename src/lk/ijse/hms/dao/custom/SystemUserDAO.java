package lk.ijse.hms.dao.custom;

import lk.ijse.hms.dao.CrudDAO;
import lk.ijse.hms.entity.SystemUser;

import java.io.IOException;

public interface SystemUserDAO extends CrudDAO<SystemUser,String> {
    boolean findUserByUserNameAndPassword(String userName,String password) throws IOException;
}
