/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:58 PM
 */
package lk.ijse.hms.bo.custom.impl;

import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.dao.DAOFactory;
import lk.ijse.hms.dao.custom.RoomDAO;
import lk.ijse.hms.dto.RoomDTO;
import lk.ijse.hms.entity.Reserve;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.service.DataConvertor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RoomBOImpl implements RoomBO {
    //DI
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOM);
    DataConvertor dataConvertor = DataConvertor.getInstance();

    public RoomBOImpl() throws IOException {
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        Function<Room,RoomDTO> function = (r)->new RoomDTO(
                r.getRoomId(),r.getType(),r.getMonthlyRental(),r.getAvailability(),r.getKeyMoney()
        );
        return dataConvertor.convert(roomDAO.findAll(),function);
    }

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.add(new Room(
                roomDTO.getRoomId(), roomDTO.getType(), roomDTO.getMonthlyRental(), roomDTO.getAvailability(),
                new ArrayList<Reserve>(), roomDTO.getKeyMoney()
        ));
    }

    @Override
    public boolean deleteRoom(String roomId) throws Exception {
        return roomDAO.delete(roomId);
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(
                roomDTO.getRoomId(), roomDTO.getType(), roomDTO.getMonthlyRental(), roomDTO.getAvailability(),
                new ArrayList(), roomDTO.getKeyMoney()
        ));
    }

    @Override
    public List<String> getAllRoomIds() throws Exception {
        return roomDAO.findAllIds();
    }

    @Override
    public RoomDTO getRoom(String roomId) throws Exception {
        Room room = roomDAO.find(roomId);
        return new RoomDTO(room.getRoomId(),room.getType(), room.getMonthlyRental(), room.getAvailability(),room.getKeyMoney());
    }

    @Override
    public String getLastRoomId() throws Exception {
        return roomDAO.findLastId();
    }

    @Override
    public Long getAddedRoomCountByType(String roomType) throws IOException {
        return roomDAO.getAddedRoomCountByType(roomType);
    }

    @Override
    public boolean updateRoomByType(String newType, String oldType) throws IOException {
        return roomDAO.updateRoomByType(newType,oldType);
    }
}
