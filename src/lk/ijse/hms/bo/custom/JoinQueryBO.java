package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.CustomDTO;

import java.io.IOException;

public interface JoinQueryBO extends SuperBO {

    CustomDTO getRoomMonthlyRentalByReservationId(String reservationId) throws IOException;
}
