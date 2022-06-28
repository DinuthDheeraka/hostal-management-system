package lk.ijse.hms.dao.custom;

import lk.ijse.hms.dao.CrudDAO;
import lk.ijse.hms.entity.Room;

import java.io.IOException;

public interface RoomDAO extends CrudDAO<Room,String> {
    Long getAddedRoomCountByType(String room) throws IOException;
    boolean updateRoomByType(String newType,String oldType) throws IOException;
}
