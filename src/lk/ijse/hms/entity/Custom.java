/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/22/2022 12:26 AM
 */
package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Custom implements SuperEntity{
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
    List<Reserve> studentReserves = new ArrayList();
    List<Payment> payments = new ArrayList();

    //Room Data
    private String roomId;
    private String type;
    private double monthlyRental;
    private String availability;
    List<Reserve> roomReserves = new ArrayList();

    //Reservation Data
    private String reserveId;
    private Date reserveDate;
    private double keyMoney;
    private Student reserveStudent;
    private Room reserveRoom;
    private String reservationStatus;
    private double paidKeyMoney;

    //Payment Data
    private String paymentId;
    private Date paymentDate;
    private String month;
    private double amountToPay;
    private double paidAmount;
    private String paymentReservationId;
    private Student paymentStudent;
}
