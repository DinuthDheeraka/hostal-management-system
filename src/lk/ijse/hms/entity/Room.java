/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 7:52 PM
 */
package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements SuperEntity{
    @Id
    @Column(length = 10)
    private String roomId;
    private String type;
    private double monthlyRental;
    private boolean availability;
    private int qty;
    @OneToMany(mappedBy = "room")
    List<Reserve> reserves = new ArrayList();
}