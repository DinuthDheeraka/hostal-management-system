package lk.ijse.hms.dao.custom;

import lk.ijse.hms.dao.CrudDAO;
import lk.ijse.hms.entity.RoomSetting;

import java.io.IOException;
import java.util.List;

public interface RoomSettingDAO extends CrudDAO<RoomSetting,String> {
    List<String> getRoomCategories() throws IOException;
    int getMaxRoomCount(String roomType) throws IOException;
}
