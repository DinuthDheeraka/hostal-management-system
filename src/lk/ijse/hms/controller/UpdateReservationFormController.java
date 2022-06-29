/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/25/2022 12:01 AM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.ReserveBO;
import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.ReserveDTO;
import lk.ijse.hms.dto.RoomDTO;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.util.RegexValidator;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UpdateReservationFormController implements Initializable {

    public JFXTextField txtReserveId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtPaidKeyMoney;
    public JFXComboBox<String> cmbxStudentIds;
    public JFXComboBox<String> cmbxRoomIds;
    public JFXComboBox<String> cmbxStatus;
    public JFXButton updateBtn;

    ReserveDTO selectedReserveDTO = new ReserveDTO();

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    public UpdateReservationFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setCmbxStudentIdsData();
            setCmbxRoomIdsData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCmbxReservationStatusData();
        updateBtn.setDisable(true);
    }

    private void setCmbxReservationStatusData() {
        cmbxStatus.setItems(FXCollections.observableArrayList("Active","Not Active"));
    }

    private ComboBox setCmbxRoomIdsData() throws Exception {
        cmbxRoomIds.setItems(FXCollections.observableArrayList(roomBO.getAllRoomIds()));
        return cmbxRoomIds;
    }

    private void setCmbxStudentIdsData() throws Exception {
        cmbxStudentIds.setItems(FXCollections.observableArrayList(studentBO.getAllStudentIds()));
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        try {
            //Update Reserved Student
            Student student = new Student();
            student.setStudentId(cmbxStudentIds.getSelectionModel().getSelectedItem());

            //Update Reserved Room
            Room room = new Room();
            room.setRoomId(cmbxRoomIds.getSelectionModel().getSelectedItem());

            ReserveDTO reserveDTO = reserveBO.getReserve(txtReserveId.getText());
            reserveDTO.setStudent(student);
            reserveDTO.setRoom(room);
            reserveDTO.setKeyMoney(Double.valueOf(txtKeyMoney.getText()));
            reserveDTO.setPaidKeyMoney(Double.valueOf(txtPaidKeyMoney.getText()));
            reserveDTO.setReservationStatus(cmbxStatus.getSelectionModel().getSelectedItem());

            reserveBO.updateReservation(reserveDTO);

            //Update Current Room Status
            RoomDTO currentRoomDTO = roomBO.getRoom(cmbxRoomIds.getSelectionModel().getSelectedItem());
            currentRoomDTO.setAvailability("Not Available");

            roomBO.updateRoom(currentRoomDTO);

            //Update Reserved Room Status(If Room changed)
            if(!this.selectedReserveDTO.getRoom().getRoomId().equals(cmbxRoomIds.getSelectionModel().getSelectedItem())){
                RoomDTO roomDTO = roomBO.getRoom(this.selectedReserveDTO.getRoom().getRoomId());
                roomDTO.setAvailability("Available");

                roomBO.updateRoom(roomDTO);
            }

            new Alert(Alert.AlertType.CONFIRMATION,"Updated Reservation Details").show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Unable to Update Reservation Details").show();
            e.printStackTrace();
        }
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void setValuesForInputFields(ReserveDTO selectedReserveDTO) {
        this.selectedReserveDTO = selectedReserveDTO;
        try {
            setCmbxRoomIdsData().getItems().add(selectedReserveDTO.getRoom().getRoomId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtReserveId.setText(selectedReserveDTO.getReserveId());
        txtKeyMoney.setText(String.valueOf(selectedReserveDTO.getKeyMoney()));
        txtPaidKeyMoney.setText(String.valueOf(selectedReserveDTO.getPaidKeyMoney()));
        cmbxStudentIds.getSelectionModel().select(selectedReserveDTO.getStudent().getStudentId());
        cmbxRoomIds.getSelectionModel().select(selectedReserveDTO.getRoom().getRoomId());
        cmbxStatus.getSelectionModel().select(selectedReserveDTO.getReservationStatus());
    }

    public void validate(KeyEvent keyEvent) {
        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

        Pattern money = Pattern.compile("([0-9]{1,}.[0-9]{2}$|[0-9]{1,}$)");
        map.put(txtKeyMoney,money);

        map.put(txtPaidKeyMoney,money);


        RegexValidator.validate(map,updateBtn);
    }
}
