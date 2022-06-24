/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/24/2022 8:32 PM
 */
package lk.ijse.hms.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveTM {
    private String reserveId;
    private String studentId;
    private String roomId;
    private Date date;
    private double keyMoney;
    private double paidKeyMoney;
    private double amountToPay;
    private String status;
}
