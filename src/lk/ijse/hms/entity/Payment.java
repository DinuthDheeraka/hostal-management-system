/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/21/2022 11:12 PM
 */
package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements SuperEntity{
    @Id
    private String paymentId;
    @CreationTimestamp
    private Date date;
    private String month;
    private double amountToPay;
    private double paidAmount;
    private String reservationId;
    @ManyToOne
    private Student student;
}
