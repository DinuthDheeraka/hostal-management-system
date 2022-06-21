/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 7:41 PM
 */
package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
public class Student implements SuperEntity{
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
    @CreationTimestamp
    private Date joinedDate;
    @OneToMany(mappedBy = "student")
    List<Reserve> reserves = new ArrayList();
    @OneToMany(mappedBy = "student")
    List<Payment> payments = new ArrayList();

    public Student(String studentId, String name, String address, String city, String district, String province, String contactNo, Date dob, String gender, Date joinedDate, List<Reserve> reserves) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.district = district;
        this.province = province;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
        this.joinedDate = joinedDate;
        this.reserves = reserves;
    }
}
