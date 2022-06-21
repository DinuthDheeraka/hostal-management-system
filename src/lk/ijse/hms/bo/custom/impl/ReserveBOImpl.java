/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:58 PM
 */
package lk.ijse.hms.bo.custom.impl;

import lk.ijse.hms.bo.custom.ReserveBO;
import lk.ijse.hms.dao.DAOFactory;
import lk.ijse.hms.dao.custom.ReserveDAO;
import lk.ijse.hms.dto.ReserveDTO;
import lk.ijse.hms.entity.Reserve;

import java.io.IOException;

public class ReserveBOImpl implements ReserveBO {

    //DI
    ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);

    public ReserveBOImpl() throws IOException {
    }

    @Override
    public boolean addReservation(ReserveDTO reserveDTO) throws Exception {
        return reserveDAO.add(new Reserve(
                reserveDTO.getReserveId(), reserveDTO.getDate(),reserveDTO.getKeyMoney(),
                reserveDTO.getStudent(),reserveDTO.getRoom(),reserveDTO.getReservationStatus(),
                reserveDTO.getPaidKeyMoney()
        ));
    }
}
