package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    List<StudentDTO> getAllStudents() throws Exception;
    List<String> getAllStudentIds() throws Exception;
    StudentDTO getStudent(String studentId) throws Exception;
    boolean deleteStudent(String studentId) throws Exception;
    boolean addStudent(StudentDTO studentDTO) throws Exception;
    boolean updateStudent(StudentDTO studentDTO) throws Exception;
    String getLastStudentId() throws Exception;
}
