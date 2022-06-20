/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:58 PM
 */
package lk.ijse.hms.bo.custom.impl;

import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dao.DAOFactory;
import lk.ijse.hms.dao.custom.StudentDAO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.service.DataConvertor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StudentBOImpl implements StudentBO {

    //DI
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    DataConvertor dataConvertor = DataConvertor.getInstance();

    public StudentBOImpl() throws IOException {
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        Function<Student,StudentDTO> function = (s)->
            new StudentDTO(
                    s.getStudentId(),s.getName(),s.getAddress(),s.getCity(),s.getDistrict(),s.getProvince(),
                    s.getContactNo(),s.getDob(),s.getGender(),s.getJoinedDate()
            );
        return  dataConvertor.convert(studentDAO.findAll(),function);
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        return studentDAO.findAllIds();
    }

    @Override
    public StudentDTO getStudent(String studentId) throws Exception {
        Student student = studentDAO.find(studentId);
        return new StudentDTO(
                student.getStudentId(),student.getName(),student.getAddress(),student.getCity(),
                student.getDistrict(),student.getProvince(),student.getContactNo(),student.getDob(),student.getGender(),
                student.getJoinedDate());
    }

    @Override
    public boolean deleteStudent(String studentId) throws Exception {
        return studentDAO.delete(studentId);
    }

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(
                studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getCity(),
                studentDTO.getDistrict(),studentDTO.getProvince(),studentDTO.getContactNo(),studentDTO.getDob(),
                studentDTO.getGender(),studentDTO.getJoinedDate(),new ArrayList()
        ));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(
                studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getCity(),
                studentDTO.getDistrict(),studentDTO.getProvince(),studentDTO.getContactNo(),studentDTO.getDob(),
                studentDTO.getGender(),studentDTO.getJoinedDate(),new ArrayList()
        ));
    }
}
