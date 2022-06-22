/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/20/2022 4:32 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.ReserveBO;
import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.ReserveDTO;
import lk.ijse.hms.dto.RoomDTO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.util.IdsGenerator;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MakeReservationFormController implements Initializable {
    public JFXComboBox<String> cmbxStudentIds;
    public JFXTextField txtStdName;
    public JFXTextField txtStdContactNo;
    public JFXTextField txtStdGender;
    public JFXTextField txtStdAddress;
    public JFXTextField txtStdCity;
    public JFXTextField txtStdDistrict;
    public JFXTextField txtStdProvince;
    public JFXTextField txtStdDOB;
    public JFXTextField txtStdRegDate;
    public JFXComboBox<String> cmbxRoomIds;
    public JFXTextField txtRoomType;
    public JFXTextField txtRoomRent;
    public JFXTextField txtRoomAvailability;
    public JFXTextField txtRoomKeyMoney;
    public JFXTextField txtReservationId;
    public JFXComboBox<String> cmbxReservationCurrentStatus;
    public JFXTextField txtRoomPaidKeyMoney;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    

    public MakeReservationFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbxRoomIdsData();
        setCmbxStudentIdsData();
        setCmbxReservationStatusData();

        cmbxStudentIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setStudentDataToTextFileds(newValue);
                    }
                }
        );

        cmbxRoomIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setRoomDataToTextFileds(newValue);
                    }
                }
        );
        setGeneratedId();
    }

    private void setCmbxReservationStatusData() {
        cmbxReservationCurrentStatus.setItems(FXCollections.observableArrayList("Active","Not Active"));
    }

    private void setRoomDataToTextFileds(String newValue) {
        try {
            RoomDTO roomDTO = roomBO.getRoom(newValue);
            txtRoomType.setText(roomDTO.getType());
            txtRoomAvailability.setText(roomDTO.getAvailability());
            txtRoomRent.setText(String.valueOf(roomDTO.getMonthlyRental()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStudentDataToTextFileds(String newValue) {
        try {
            StudentDTO studentDTO = studentBO.getStudent(newValue);
            txtStdAddress.setText(studentDTO.getAddress());
            txtStdCity.setText(studentDTO.getCity());
            txtStdDistrict.setText(studentDTO.getDistrict());
            txtStdProvince.setText(studentDTO.getProvince());
            txtStdDOB.setText(String.valueOf(studentDTO.getDob()));
            txtStdContactNo.setText(studentDTO.getContactNo());
            txtStdRegDate.setText(String.valueOf(studentDTO.getJoinedDate()));
            txtStdName.setText(studentDTO.getName());
            txtStdGender.setText(studentDTO.getGender());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCmbxStudentIdsData() {
        try {
            cmbxStudentIds.setItems(FXCollections.observableArrayList(studentBO.getAllStudentIds()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCmbxRoomIdsData() {
        try {
            cmbxRoomIds.setItems(FXCollections.observableArrayList(roomBO.getAllRoomIds()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeReservationBtnOnAction(ActionEvent actionEvent) {
        //Getting Student
        Student student = new Student();
        student.setStudentId(cmbxStudentIds.getSelectionModel().getSelectedItem());

        //Getting Room
        Room room = new Room();
        room.setRoomId(cmbxRoomIds.getSelectionModel().getSelectedItem());

        //Make Reservation
        try {
            reserveBO.addReservation(new ReserveDTO(
                    txtReservationId.getText(),null,Double.valueOf(txtRoomKeyMoney.getText()),
                    student,room, cmbxReservationCurrentStatus.getSelectionModel().getSelectedItem(),
                    Double.valueOf(txtRoomPaidKeyMoney.getText())
            ));
            setGeneratedId();
            new Alert(Alert.AlertType.CONFIRMATION,"Reservation Placed Successfully").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            e.printStackTrace();
        }
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGeneratedId(){
        try {
            txtReservationId.setText(IdsGenerator.generateId("RS-", reserveBO.getLastReservationId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
