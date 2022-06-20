/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 2:49 AM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.dto.RoomDTO;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomsFormController implements Initializable {
    public Label txtTitle;
    public JFXButton addBtn;
    public JFXTextField txtRoomId;
    public JFXTextField txtMonthlyRental;
    public JFXComboBox<String> cmbxRoomType;
    public JFXComboBox<String> cmbxRoomAvailability;


    //DI
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setRoomTypeCmbxData();
        setRoomAvailabilityCmbxData();
    }

    private void setRoomAvailabilityCmbxData() {
        cmbxRoomAvailability.setItems(FXCollections.observableArrayList("Available","Not Available"));
    }

    private void setRoomTypeCmbxData() {
        cmbxRoomType.setItems(FXCollections.observableArrayList("Non-AC","Non-AC/Food","AC","AC/Food"));
    }

    public AddRoomsFormController() throws IOException {
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void addRoomBtnOnAction(ActionEvent actionEvent) throws Exception {

        if("UPDATE ROOM".equals(addBtn.getText())){
                roomBO.updateRoom(new RoomDTO(
                        txtRoomId.getText(),cmbxRoomType.getSelectionModel().getSelectedItem(),
                        Double.valueOf(txtMonthlyRental.getText()),cmbxRoomAvailability.getSelectionModel().getSelectedItem()
            ));
        } else{
            try {
                roomBO.addRoom(new RoomDTO(
                        txtRoomId.getText(),cmbxRoomType.getSelectionModel().getSelectedItem(),
                        Double.valueOf(txtMonthlyRental.getText()),cmbxRoomAvailability.getSelectionModel().getSelectedItem()
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setValuesForInputFields(RoomDTO roomDTO) {
        txtTitle.setText("UPDATE ROOM");
        addBtn.setText("UPDATE ROOM");
        txtRoomId.setText(roomDTO.getRoomId());
        txtMonthlyRental.setText(String.valueOf(roomDTO.getMonthlyRental()));
        cmbxRoomType.getSelectionModel().select(roomDTO.getType());
        cmbxRoomAvailability.getSelectionModel().select(roomDTO.getAvailability());
    }
}
