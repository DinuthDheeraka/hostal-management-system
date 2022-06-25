/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/25/2022 10:28 PM
 */
package lk.ijse.hms.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTM {
    private String paymentId;
    private Date date;
    private String month;
    private double amountToPay;
    private double paidAmount;
    private double balance;
    private String reservationId;
    private String studentId;
}
