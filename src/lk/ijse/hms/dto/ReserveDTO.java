/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/21/2022 3:55 PM
 */
package lk.ijse.hms.dto;

import lk.ijse.hms.entity.Room;
import lk.ijse.hms.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDTO {
    private String reserveId;
    private Date date;
    private double keyMoney;
    private Student student;
    private Room room;
    private String reservationStatus;
    private double paidKeyMoney;
}
