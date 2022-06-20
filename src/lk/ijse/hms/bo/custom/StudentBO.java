package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    List<StudentDTO> getAllStudents() throws Exception;
    List<String> getAllStudentIds() throws Exception;
}
