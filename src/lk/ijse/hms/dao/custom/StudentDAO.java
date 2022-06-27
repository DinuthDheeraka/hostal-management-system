package lk.ijse.hms.dao.custom;

import lk.ijse.hms.dao.CrudDAO;
import lk.ijse.hms.entity.Student;

import java.io.IOException;
import java.math.BigInteger;

public interface StudentDAO extends CrudDAO<Student,String> {
    BigInteger getStudentJoinedCountByMonth(String month) throws IOException;
}
