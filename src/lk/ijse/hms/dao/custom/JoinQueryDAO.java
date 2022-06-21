package lk.ijse.hms.dao.custom;

import lk.ijse.hms.dao.SuperDAO;
import lk.ijse.hms.entity.Custom;

import java.io.IOException;

public interface JoinQueryDAO extends SuperDAO {

    Custom getRoomMonthlyRentalByReservationId(String reservationId) throws IOException;
}
