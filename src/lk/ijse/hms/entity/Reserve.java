/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 7:50 PM
 */
package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve implements SuperEntity{
    @Id
    @Column(length = 10)
    private String reserveId;
    @Column(nullable = false)
    @CreationTimestamp
    private Date date;
    private double keyMoney;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Room room;
    private boolean reservationStatus;
    private boolean paymentStatus;
    private double paidKeyMoney;
}
