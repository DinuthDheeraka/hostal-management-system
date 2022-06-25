/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/26/2022 2:37 AM
 */
package lk.ijse.hms.bo.custom.impl;

import lk.ijse.hms.bo.custom.SystemUserBO;
import lk.ijse.hms.dao.DAOFactory;
import lk.ijse.hms.dao.custom.SystemUserDAO;
import lk.ijse.hms.dto.SystemUserDTO;
import lk.ijse.hms.entity.SystemUser;

import java.io.IOException;

public class SystemUserBOImpl implements SystemUserBO {

    //DI
    SystemUserDAO systemUserDAO = (SystemUserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SYSTEM_USER);

    public SystemUserBOImpl() throws IOException {
    }

    @Override
    public SystemUserDTO getSystemUser(String userId) throws Exception {
        SystemUser systemUser = systemUserDAO.find(userId);
        return new SystemUserDTO(systemUser.getUserId(),systemUser.getUserName(),systemUser.getPassword());
    }

    @Override
    public boolean updateSystemUser(SystemUserDTO systemUserDTO) throws Exception {
        return systemUserDAO.update(new SystemUser(systemUserDTO.getUserId(),systemUserDTO.getUserName(),systemUserDTO.getPassword()));
    }

    @Override
    public boolean addSystemUser(SystemUserDTO systemUserDTO) throws Exception {
        return systemUserDAO.add(new SystemUser(systemUserDTO.getUserId(),systemUserDTO.getUserName(),systemUserDTO.getPassword()));
    }

    @Override
    public String getLastUserId() throws Exception {
        return systemUserDAO.findLastId();
    }

    @Override
    public boolean getSystemUserByUserNameAndPassword(String userName, String password) throws IOException {
        return systemUserDAO.findUserByUserNameAndPassword(userName,password);
    }
}
