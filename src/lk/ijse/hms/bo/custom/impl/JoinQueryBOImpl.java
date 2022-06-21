/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/22/2022 12:56 AM
 */
package lk.ijse.hms.bo.custom.impl;

import lk.ijse.hms.bo.custom.JoinQueryBO;
import lk.ijse.hms.dao.DAOFactory;
import lk.ijse.hms.dao.custom.JoinQueryDAO;
import lk.ijse.hms.dto.CustomDTO;

import java.io.IOException;

public class JoinQueryBOImpl implements JoinQueryBO {

    //DI
    JoinQueryDAO joinQueryDAO = (JoinQueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.JOIN_QUERY);

    public JoinQueryBOImpl() throws IOException {
    }

    @Override
    public CustomDTO getRoomMonthlyRentalByReservationId(String reservationId) throws IOException {
        CustomDTO customDTO = new CustomDTO();
        customDTO.setMonthlyRental(joinQueryDAO.getRoomMonthlyRentalByReservationId(reservationId).getMonthlyRental());
        return customDTO;
    }
}
