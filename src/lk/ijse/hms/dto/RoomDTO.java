/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/19/2022 2:05 AM
 */
package lk.ijse.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDTO {
    private String roomId;
    private String type;
    private double monthlyRental;
    private String availability;
    private double keyMoney;
}
