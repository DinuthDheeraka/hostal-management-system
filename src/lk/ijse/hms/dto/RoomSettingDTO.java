/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/23/2022 6:33 PM
 */
package lk.ijse.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSettingDTO {
    private String roomCategoryId;
    private String type;
    private Integer maxCount;
}
