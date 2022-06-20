/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/17/2022 11:25 PM
 */
package lk.ijse.hms.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.dto.RoomDTO;
import lk.ijse.hms.service.DataConvertor;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.view.tdm.RoomTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class RoomsFormController implements Initializable {
    public TableView<RoomTM> roomTbl;
    public TableColumn colRoomId;
    public TableColumn colMonthlyRental;
    public TableColumn colRoomType;
    public TableColumn colAvailability;
    public TextField txtSearchBar;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    //selected Room Data
    private String selectedRoomId;
    private double selectedMonthlyRental;
    private String selectedRoomType;
    private String selectedAvailability;

    //DI
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    DataConvertor dataConvertor = DataConvertor.getInstance();

    public RoomsFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colRoomId.setCellValueFactory(new PropertyValueFactory("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory("type"));
        colMonthlyRental.setCellValueFactory(new PropertyValueFactory("monthlyRental"));
        colAvailability.setCellValueFactory(new PropertyValueFactory("availability"));

        roomTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedRoomData(newValue);
                });

        try {
            setRoomsTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSelectedRoomData(RoomTM newValue) {
        selectedRoomId = newValue.getRoomId();
        selectedRoomType = newValue.getType();
        selectedMonthlyRental = newValue.getMonthlyRental();
        selectedAvailability = newValue.getAvailability();
    }

    private void setRoomsTblData() throws Exception {
        Function<RoomDTO,RoomTM> function = (dto)->new RoomTM(
                dto.getRoomId(),dto.getType(),dto.getMonthlyRental(),dto.getAvailability()
        );
        roomTbl.getItems().clear();
        roomTbl.setItems(FXCollections.observableArrayList(dataConvertor.convert(roomBO.getAllRooms(),function)));
    }

    public void backToHomeBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewRoomBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().setNewStage("Add-Rooms-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshCtxmOnAction(ActionEvent actionEvent) throws Exception {
        try {
            setRoomsTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCtxmOnAction(ActionEvent actionEvent) {
        try {
            roomBO.deleteRoom(selectedRoomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCtxmOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Add-Rooms-Form.fxml"));

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Transfer Room Data to Update Form
        AddRoomsFormController controller = fxmlLoader.getController();
        controller.setValuesForInputFields(new RoomDTO(selectedRoomId,selectedRoomType,selectedMonthlyRental,selectedAvailability));

        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);

        Navigations.getInstance().transparentUi(stage,scene);
    }
}
