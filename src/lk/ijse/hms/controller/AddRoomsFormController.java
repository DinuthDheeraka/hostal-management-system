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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.bo.custom.RoomSettingBO;
import lk.ijse.hms.dto.RoomDTO;
import lk.ijse.hms.util.IdsGenerator;
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
    public JFXTextField txtKeyMoney;


    //DI
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    RoomSettingBO roomSettingBO = (RoomSettingBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM_SETTING_BO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setRoomTypeCmbxData();
        setRoomAvailabilityCmbxData();
        setGeneratedId();

        try {
            txtRoomId.setText(IdsGenerator.generateId("RM-",roomBO.getLastRoomId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbxRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        isEligibleToAddNewRoom();
                    }
                }
        );
    }

    private void setRoomAvailabilityCmbxData() {
        cmbxRoomAvailability.setItems(FXCollections.observableArrayList("Available","Not Available"));
    }

    private void setRoomTypeCmbxData() {
        try {
            cmbxRoomType.setItems(FXCollections.observableArrayList(roomSettingBO.getRoomCategories()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //cmbxRoomType.setItems(FXCollections.observableArrayList("Non-AC","Non-AC/Food","AC","AC/Food"));
    }

    public AddRoomsFormController() throws IOException {
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void addRoomBtnOnAction(ActionEvent actionEvent) throws Exception {

        //Update Room
        if("UPDATE ROOM".equals(addBtn.getText())){
                roomBO.updateRoom(new RoomDTO(
                        txtRoomId.getText(),cmbxRoomType.getSelectionModel().getSelectedItem(),
                        Double.valueOf(txtMonthlyRental.getText()),cmbxRoomAvailability.getSelectionModel().getSelectedItem(),Double.valueOf(txtKeyMoney.getText())
            ));
                new Alert(Alert.AlertType.CONFIRMATION,"Updated Room Successfully").show();
        } else{

            //Add New Room
            if(isEligibleToAddNewRoom()){
                try {
                    roomBO.addRoom(new RoomDTO(
                            txtRoomId.getText(),cmbxRoomType.getSelectionModel().getSelectedItem(),
                            Double.valueOf(txtMonthlyRental.getText()),cmbxRoomAvailability.getSelectionModel().getSelectedItem(),Double.valueOf(txtKeyMoney.getText())
                    ));
                    setGeneratedId();
                    new Alert(Alert.AlertType.ERROR,"Added Room Successfully").show();

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,"Unable to Update Room!!").show();
                    e.printStackTrace();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Max Room Count Reached").show();
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
        txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
    }

    public void setGeneratedId(){
        try {
            txtRoomId.setText(IdsGenerator.generateId("RM-", roomBO.getLastRoomId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isEligibleToAddNewRoom(){
        try {
            return roomBO.getAddedRoomCountByType(cmbxRoomType.getSelectionModel().getSelectedItem())< roomSettingBO.getMaxRoomCount(cmbxRoomType.getSelectionModel().getSelectedItem());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
