/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/23/2022 4:22 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.bo.custom.RoomSettingBO;
import lk.ijse.hms.dto.RoomSettingDTO;
import lk.ijse.hms.service.DataConvertor;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.util.RegexValidator;
import lk.ijse.hms.view.tdm.RoomSettingTM;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.regex.Pattern;

public class RoomSettingsFormController implements Initializable {
    public TextField txtSearchBar;
    public JFXButton addBtn;
    public TableView<RoomSettingTM> roomCategoryTbl;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colMaxCount;
    public JFXTextField txtId;
    public JFXTextField txtType;
    public JFXTextField txtMaxCount;
    public JFXButton addCtrlBtn;

    private String selectedRoomType;

    //DI
    DataConvertor dataConvertor = DataConvertor.getInstance();
    RoomSettingBO roomSettingBO = (RoomSettingBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM_SETTING_BO);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);

    public RoomSettingsFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory("roomCategoryId"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colMaxCount.setCellValueFactory(new PropertyValueFactory("maxCount"));

        try {
            setRoomSettingTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        roomCategoryTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedRoomCategoryData(newValue);
                });
    }

    private void setSelectedRoomCategoryData(RoomSettingTM newValue) {
        addBtn.setText("UPDATE");
        txtId.setText(newValue.getRoomCategoryId());
        txtType.setText(newValue.getType());
        txtMaxCount.setText(String.valueOf(newValue.getMaxCount()));
        txtId.setEditable(false);
        selectedRoomType = newValue.getType();
    }

    private void setRoomSettingTblData() throws Exception {
        Function<RoomSettingDTO, RoomSettingTM> function = (dto)->
                new RoomSettingTM(
                        dto.getRoomCategoryId(),dto.getType(),dto.getMaxCount()
                );
        roomCategoryTbl.setItems(FXCollections.observableArrayList(dataConvertor.convert(roomSettingBO.getAllRoomSettings(),function)));
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
    }

    public void addBtnOnAction(ActionEvent actionEvent){
        if("UPDATE".equals(addBtn.getText())){
            try {
                roomSettingBO.updateRoomSetting(
                        new RoomSettingDTO(txtId.getText(),txtType.getText(),Integer.valueOf(txtMaxCount.getText()))
                );
                new Alert(Alert.AlertType.CONFIRMATION,"Updated Room Setting").show();

                roomBO.updateRoomByType(txtType.getText(),selectedRoomType);
                System.out.println(selectedRoomType);

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Unable to Update Room Setting").show();
                e.printStackTrace();
            }

        }else{
            try {
                roomSettingBO.addRoomSetting(new RoomSettingDTO(
                        txtId.getText(),txtType.getText(),Integer.valueOf(txtMaxCount.getText())
                ));
                new Alert(Alert.AlertType.CONFIRMATION,"Added Room Setting Successfully").show();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Unable to Add Room Setting").show();
                e.printStackTrace();
            }
        }
    }

    public void addCtrlBtnOnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        addBtn.setText("ADD");
    }

    public void refreshCtxmOnAction(ActionEvent actionEvent) {
        try {
            setRoomSettingTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCtxmOnAction(ActionEvent actionEvent) {
        try {
            roomSettingBO.deleteRoomSetting(txtId.getText());
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Room Setting").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Unable to Delete Room Setting").show();
            e.printStackTrace();
        }
    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void validate(MouseEvent mouseEvent) {

        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

        Pattern id = Pattern.compile("RM-[0-9]{4}");
        map.put(txtId,id);

        Pattern type = Pattern.compile("[0-9 A-Za-z/ - .()]{2,}");
        map.put(txtType,type);

        Pattern maxCount = Pattern.compile("[0-9]{1,}");
        map.put(txtMaxCount,maxCount);

        RegexValidator.validate(map,addBtn);
    }
}
