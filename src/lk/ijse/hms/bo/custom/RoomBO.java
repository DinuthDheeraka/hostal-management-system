package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.RoomDTO;

import java.io.IOException;
import java.util.List;

public interface RoomBO extends SuperBO {

    List<RoomDTO> getAllRooms() throws Exception;
    boolean addRoom(RoomDTO roomDTO) throws Exception;
    boolean deleteRoom(String roomId) throws Exception;
    boolean updateRoom(RoomDTO roomDTO) throws Exception;
    List<String> getAllRoomIds() throws Exception;
    RoomDTO getRoom(String roomId) throws Exception;
    String getLastRoomId() throws Exception;
    Long getAddedRoomCountByType(String roomType) throws IOException;
    boolean updateRoomByType(String newType,String oldType) throws IOException;
}
