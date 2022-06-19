/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 9:26 PM
 */
package lk.ijse.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String studentId;
    private String name;
    private String address;
    private String city;
    private String district;
    private String province;
    private String contactNo;
    private Date dob;
    private String gender;
    private Date joinedDate;
}
