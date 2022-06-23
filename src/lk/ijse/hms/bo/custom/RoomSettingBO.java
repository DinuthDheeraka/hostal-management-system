package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.RoomSettingDTO;

import java.io.IOException;
import java.util.List;

public interface RoomSettingBO extends SuperBO {

    List<RoomSettingDTO> getAllRoomSettings() throws Exception;
    List<String> getRoomCategories() throws IOException;
}
