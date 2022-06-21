/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/22/2022 12:26 AM
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
public class CustomDTO {
    //Student Data
    private String studentId;
    private String name;
    private String address;
    private String city;
    private String district;
    private String province;
    private String contactNo;
    private Date dob;
    private String gender;
    private Date joinedDate;

    //Room Data
    private String roomId;
    private String type;
    private double monthlyRental;
    private String availability;

    //Reservation Data
    private String reserveId;
    private Date reserveDate;
    private double keyMoney;
    private Student student;
    private Room room;
    private String reservationStatus;
    private double paidKeyMoney;

    //Payment Data
    private String paymentId;
    private Date paymentDate;
    private String month;
    private double amountToPay;
    private double paidAmount;
    private String reservationId;
    private Student paymentStudent;
}
