/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 7:41 PM
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
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(length = 10)
    private String studentId;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String city;
    private String district;
    private String province;
    @Column(length = 10)
    private String contactNo;
    private Date dob;
    private String gender;
    @OneToMany(mappedBy = "studentId")
    List<Reserve> reserves = new ArrayList();
}
