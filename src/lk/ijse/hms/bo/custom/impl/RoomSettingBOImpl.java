/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/23/2022 6:39 PM
 */
package lk.ijse.hms.bo.custom.impl;

import lk.ijse.hms.bo.custom.RoomSettingBO;
import lk.ijse.hms.dao.DAOFactory;
import lk.ijse.hms.dao.custom.RoomSettingDAO;
import lk.ijse.hms.dto.RoomSettingDTO;
import lk.ijse.hms.entity.RoomSetting;
import lk.ijse.hms.service.DataConvertor;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class RoomSettingBOImpl implements RoomSettingBO {
    //DI
    DataConvertor dataConvertor = DataConvertor.getInstance();
    RoomSettingDAO roomSettingDAO = (RoomSettingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOM_SETTING);

    public RoomSettingBOImpl() throws IOException {
    }

    @Override
    public List<RoomSettingDTO> getAllRoomSettings() throws Exception {
        Function<RoomSetting, RoomSettingDTO> function = (r)->new RoomSettingDTO(
                r.getRoomCategoryId(),r.getType(),r.getMaxCount()
        );
        return dataConvertor.convert(roomSettingDAO.findAll(),function);
    }

    @Override
    public List<String> getRoomCategories() throws IOException {
        return roomSettingDAO.getRoomCategories();
    }

    @Override
    public int getMaxRoomCount(String roomType) throws IOException {
        return roomSettingDAO.getMaxRoomCount(roomType);
    }
}
