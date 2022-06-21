package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.ReserveDTO;

public interface ReserveBO extends SuperBO {
    boolean addReservation(ReserveDTO reserveDTO) throws Exception;
}
