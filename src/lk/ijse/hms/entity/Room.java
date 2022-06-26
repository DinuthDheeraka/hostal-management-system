/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 7:52 PM
 */
package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String availability;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    List<Reserve> reserves = new ArrayList();
    private double keyMoney;
}
