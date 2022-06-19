/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/17/2022 5:58 PM
 */
package lk.ijse.hms.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.service.DataConvertor;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.view.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class StudentsFormController implements Initializable {
    public TableView<StudentTM> studentTbl;
    public TableColumn colStdId;
    public TableColumn colStdName;
    public TableColumn colStdDOB;
    public TableColumn colStdContactNo;
    public TableColumn colStdAddress;
    public TableColumn colStdCity;
    public TableColumn colStdDistrict;
    public TableColumn colStdProvince;
    public TableColumn colStdGender;
    public TableColumn colStdJoinedDate;
    public TextField txtSearchBar;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    DataConvertor dataConvertor = DataConvertor.getInstance();

    public StudentsFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStdId.setCellValueFactory(new PropertyValueFactory("studentId"));
        colStdName.setCellValueFactory(new PropertyValueFactory("name"));
        colStdGender.setCellValueFactory(new PropertyValueFactory("gender"));
        colStdDOB.setCellValueFactory(new PropertyValueFactory("DOB"));
        colStdContactNo.setCellValueFactory(new PropertyValueFactory("contactNo"));
        colStdAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colStdCity.setCellValueFactory(new PropertyValueFactory("city"));
        colStdDistrict.setCellValueFactory(new PropertyValueFactory("district"));
        colStdProvince.setCellValueFactory(new PropertyValueFactory("province"));
        colStdJoinedDate.setCellValueFactory(new PropertyValueFactory("joinedDate"));
        try {
            setStudentTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStudentTblData() throws Exception {
        Function<StudentDTO,StudentTM> function = (dto)->
            new StudentTM(
                    dto.getStudentId(),dto.getName(),dto.getDob(),dto.getContactNo(),
                    dto.getAddress(),dto.getCity(),dto.getDistrict(), dto.getProvince(),
                    dto.getGender(),dto.getJoinedDate()
            );
        studentTbl.setItems(FXCollections.observableArrayList(dataConvertor.convert(studentBO.getAllStudents(),function)));
    }

    public void backToHomeBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().setNewStage("Add-Student-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
