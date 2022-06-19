/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 2:48 AM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import lk.ijse.hms.util.Navigations;

public class AddStudentFormController {
    public Label txtTitle;
    public JFXTextField txtStdId;
    public JFXTextField txtName;
    public JFXTextField txtContactNo;
    public JFXDatePicker dpkDOB;
    public JFXTextField txtAddress;
    public JFXComboBox cmbxCity;
    public JFXComboBox cmbxDistrict;
    public JFXComboBox cmbxProvince;
    public JFXComboBox cmbxGender;

    public void canselBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }
}
