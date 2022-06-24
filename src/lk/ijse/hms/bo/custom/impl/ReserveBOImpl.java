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
import lk.ijse.hms.service.DataConvertor;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class ReserveBOImpl implements ReserveBO {

    //DI
    ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);
    DataConvertor dataConvertor = DataConvertor.getInstance();

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

    @Override
    public List<String> getAllReservationIds() throws Exception {
        return reserveDAO.findAllIds();
    }

    @Override
    public String getLastReservationId() throws Exception {
        return reserveDAO.findLastId();
    }

    @Override
    public List<ReserveDTO> getAllReservations() throws Exception {
        Function<Reserve,ReserveDTO> function =(r)->new ReserveDTO(
                r.getReserveId(),r.getDate(),r.getKeyMoney(),r.getStudent(),r.getRoom(),
                r.getReservationStatus(),r.getPaidKeyMoney()
        );
        return dataConvertor.convert(reserveDAO.findAll(), function);
    }
}
