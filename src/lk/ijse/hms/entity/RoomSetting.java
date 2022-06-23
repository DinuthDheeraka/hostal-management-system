/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/23/2022 4:10 PM
 */
package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSetting {
    @Id
    private String roomCategoryId;
    private String type;
    private double maxCount;
}
