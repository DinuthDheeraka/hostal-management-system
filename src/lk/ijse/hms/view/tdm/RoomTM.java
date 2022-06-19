/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/19/2022 2:14 AM
 */
package lk.ijse.hms.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTM {
    private String roomId;
    private String type;
    private double monthlyRental;
    private String availability;
}
