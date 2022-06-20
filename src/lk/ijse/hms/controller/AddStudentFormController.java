/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 2:48 AM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.hms.util.Navigations;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbxCityData();
        setCmbxDistrictData();
        setCmbxProvinceData();
        setCmbxGenderData();
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
                "Jaffna","Matara","Anuradhapura","Ratnapura","Puttalam","Badulla","Mullaittivu","Matale","Mannar","Kurunegala"
        ));
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }
}
