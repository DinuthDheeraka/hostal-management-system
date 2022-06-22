/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 2:48 AM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.util.IdsGenerator;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddStudentFormController implements Initializable {
    public Label txtTitle;
    public JFXTextField txtStdId;
    public JFXTextField txtName;
    public JFXTextField txtContactNo;
    public JFXDatePicker dpkDOB;
    public JFXTextField txtAddress;
    public JFXComboBox<String> cmbxCity;
    public JFXComboBox<String> cmbxDistrict;
    public JFXComboBox<String> cmbxProvince;
    public JFXComboBox<String> cmbxGender;
    public JFXButton addBtn;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public AddStudentFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbxCityData();
        setCmbxDistrictData();
        setCmbxProvinceData();
        setCmbxGenderData();
        setGeneratedId();
    }

    private void setCmbxGenderData() {
        cmbxGender.setItems(FXCollections.observableArrayList("Male","Female"));
    }

    private void setCmbxProvinceData() {
        cmbxProvince.setItems(FXCollections.observableArrayList(
                "Central Province","Eastern Province","Northern Province","Southern Province",
                "Western Province","North Western Province","North Central Province","Uva Province",
                "Sabaragamuwa Province"
        ));
    }

    private void setCmbxDistrictData() {
        cmbxDistrict.setItems(FXCollections.observableArrayList(
                "Colombo","Gampaha","Kalutara","Kandy","Matale","Nuwara Eliya","Galle","Matara","Hambantota","Jaffna","Kilinochchi",
        "Mannar", "Vavuniya", "Mullaitivu", "Batticaloa", "Ampara", "Trincomalee", "Kurunegala", "Puttalam", "Anuradhapura", "Polonnaruwa",
        "Badulla", "Moneragala", "Ratnapura", "Kegalle"
        ));
    }

    private void setCmbxCityData() {
        cmbxCity.setItems(FXCollections.observableArrayList(
                "Colombo","Moratuwa","Kandy","Negombo","Negombo","Sri Jayewardenepura Kotte","Sri Jayewardenepura Kotte","Galle","Trincomalee",
                "Jaffna","Matara","Anuradhapura","Ratnapura","Puttalam","Badulla","Mullaittivu","Matale","Mannar","Kurunegala","Kalutara"
        ));
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void setValuesForInputFields(StudentDTO studentDTO) {
        txtTitle.setText("UPDATE STUDENT");
        addBtn.setText("UPDATE STUDENT");
        txtStdId.setText(studentDTO.getStudentId());
        txtContactNo.setText(studentDTO.getContactNo());
        txtName.setText(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        cmbxCity.getSelectionModel().select(studentDTO.getCity());
        cmbxDistrict.getSelectionModel().select(studentDTO.getDistrict());
        cmbxProvince.getSelectionModel().select(studentDTO.getProvince());
        cmbxGender.getSelectionModel().select(studentDTO.getGender());
        dpkDOB.setValue(LocalDate.parse(String.valueOf(studentDTO.getDob()).substring(0,10)));
    }

    public void addStudentBtnOnAction(ActionEvent actionEvent) {
        if("UPDATE STUDENT".equals(addBtn.getText())){
            try {
                StudentDTO studentDTO = studentBO.getStudent(txtStdId.getText());
                studentBO.updateStudent(new StudentDTO(
                        txtStdId.getText(),txtName.getText(),txtAddress.getText(),
                        cmbxCity.getSelectionModel().getSelectedItem(),cmbxDistrict.getSelectionModel().getSelectedItem(),
                        cmbxProvince.getSelectionModel().getSelectedItem(),txtContactNo.getText(),
                        Date.from(dpkDOB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),cmbxGender.getSelectionModel().getSelectedItem(),
                        studentDTO.getJoinedDate()
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                studentBO.addStudent(new StudentDTO(
                        txtStdId.getText(),txtName.getText(),txtAddress.getText(),
                        cmbxCity.getSelectionModel().getSelectedItem(),cmbxDistrict.getSelectionModel().getSelectedItem(),
                        cmbxProvince.getSelectionModel().getSelectedItem(),txtContactNo.getText(),
                        Date.from(dpkDOB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),cmbxGender.getSelectionModel().getSelectedItem(),
                        null
                ));
                setGeneratedId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setGeneratedId(){
        try {
            txtStdId.setText(IdsGenerator.generateId("ST-",studentBO.getLastStudentId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
