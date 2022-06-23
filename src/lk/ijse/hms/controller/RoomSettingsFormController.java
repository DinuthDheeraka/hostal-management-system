/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/23/2022 4:22 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomSettingsFormController implements Initializable {
    public TextField txtSearchBar;
    public JFXButton addBtn;
    public TableView roomCategoryTbl;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colMaxCount;
    public JFXTextField txtId;
    public JFXTextField txtType;
    public JFXTextField txtMaxCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
    }

    public void addBtnOnAction(ActionEvent actionEvent) {
    }
}
