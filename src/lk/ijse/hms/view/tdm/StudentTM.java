/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 9:40 PM
 */
package lk.ijse.hms.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTM {

    private String studentId;
    private String name;
    private Date DOB;
    private String contactNo;
    private String address;
    private String city;
    private String district;
    private String province;
    private String gender;
    private Date joinedDate;
}
