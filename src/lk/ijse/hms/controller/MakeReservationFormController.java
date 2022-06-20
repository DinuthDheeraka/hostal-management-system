/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/20/2022 4:32 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.StudentBO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MakeReservationFormController implements Initializable {
    public JFXComboBox cmbxStudentIds;
    public JFXTextField txtStdName;
    public JFXTextField txtStdContactNo;
    public JFXTextField txtStdGender;
    public JFXTextField txtStdAddress;
    public JFXTextField txtStdCity;
    public JFXTextField txtStdDistrict;
    public JFXTextField txtStdProvince;
    public JFXTextField txtStdDOB;
    public JFXTextField txtStdRegDate;
    public JFXComboBox cmbxRoomIds;
    public JFXTextField txtRoomType;
    public JFXTextField txtRoomRent;
    public JFXTextField txtRoomAvailability;
    public JFXTextField txtRoomKeyMoney;
    public JFXTextField txtReservationId;
    public JFXComboBox cmbxReservationCurrentStatus;
    public JFXComboBox txtRoomKeyMoneyStatus;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public MakeReservationFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbxRoomIdsData();
        setCmbxStudentIdsData();
    }

    private void setCmbxStudentIdsData() {
    }

    private void setCmbxRoomIdsData() {
    }

    public void makeReservationBtnOnAction(ActionEvent actionEvent) {
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
    }
}
