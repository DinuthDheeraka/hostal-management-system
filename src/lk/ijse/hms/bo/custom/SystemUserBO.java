package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.SystemUserDTO;

import java.io.IOException;

public interface SystemUserBO extends SuperBO {
    SystemUserDTO getSystemUser(String userId) throws Exception;
    boolean updateSystemUser(SystemUserDTO systemUserDTO) throws Exception;
    boolean addSystemUser(SystemUserDTO systemUserDTO) throws Exception;
    String getLastUserId() throws Exception;
    boolean isExistsSystemUserByUserNameAndPassword(String userName,String password) throws IOException;
    SystemUserDTO getSystemUserByUserNameAndPassword(String userName,String password) throws IOException;
    boolean isPasswordExists(String password) throws IOException;
}
