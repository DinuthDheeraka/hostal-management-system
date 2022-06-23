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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.RoomSettingBO;
import lk.ijse.hms.dto.RoomSettingDTO;
import lk.ijse.hms.service.DataConvertor;
import lk.ijse.hms.view.tdm.RoomSettingTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

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

    //DI
    DataConvertor dataConvertor = DataConvertor.getInstance();
    RoomSettingBO roomSettingBO = (RoomSettingBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM_SETTING_BO);

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

    public void addBtnOnAction(ActionEvent actionEvent) {
    }

    public void addCtrlBtnOnAction(ActionEvent actionEvent) {
        addBtn.setText("ADD");
    }
}
