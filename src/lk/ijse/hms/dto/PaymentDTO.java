/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/22/2022 12:09 AM
 */
package lk.ijse.hms.dto;

import lk.ijse.hms.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private String paymentId;
    private Date date;
    private String month;
    private double amountToPay;
    private double paidAmount;
    private String reservationId;
    private Student student;
}
