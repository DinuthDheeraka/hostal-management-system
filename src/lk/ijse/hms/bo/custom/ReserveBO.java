package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.ReserveDTO;

import java.util.List;

public interface ReserveBO extends SuperBO {
    boolean addReservation(ReserveDTO reserveDTO) throws Exception;
    List<String> getAllReservationIds() throws Exception;
    String getLastReservationId() throws Exception;
    List<ReserveDTO> getAllReservations() throws Exception;
    ReserveDTO getReserve(String reserveId) throws Exception;
    boolean updateReservation(ReserveDTO reserveDTO) throws Exception;
    boolean deleteReservation(String id) throws Exception;
}
