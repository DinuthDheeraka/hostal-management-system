/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/20/2022 4:32 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.ReserveBO;
import lk.ijse.hms.bo.custom.RoomBO;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.ReserveDTO;
import lk.ijse.hms.dto.RoomDTO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.service.DataConvertor;
import lk.ijse.hms.util.IdsGenerator;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.view.tdm.ReserveTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public TableView<ReserveTM> reserveTbl;
    public TableColumn colReserveId;
    public TableColumn colStdId;
    public TableColumn colRoomId;
    public TableColumn colDate;
    public TableColumn colKeyMoney;
    public TableColumn colPaidKeyMoney;
    public TableColumn colAmountToPay;
    public TableColumn colStatus;
    public JFXComboBox<String> cmbxPaymentStatus;

    //Selected Reservation Data
    private ReserveDTO selectedReserveDTO;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    DataConvertor dataConvertor = DataConvertor.getInstance();
    

    public MakeReservationFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStdId.setCellValueFactory(new PropertyValueFactory("studentId"));
        colReserveId.setCellValueFactory(new PropertyValueFactory("reserveId"));
        colRoomId.setCellValueFactory(new PropertyValueFactory("roomId"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colAmountToPay.setCellValueFactory(new PropertyValueFactory("amountToPay"));
        colPaidKeyMoney.setCellValueFactory(new PropertyValueFactory("paidKeyMoney"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory("keyMoney"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));

        setCmbxRoomIdsData();
        setCmbxStudentIdsData();
        setCmbxPaymentStatusData();
        setCmbxReservationStatusData();

        try {
            setReservationTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        cmbxPaymentStatus.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setFilteredDataByPaymentStatus(newValue);
                    }
                }
        );

        reserveTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedReservationData(newValue);
                });

        setGeneratedId();
    }

    private void setFilteredDataByPaymentStatus(String newValue) {
        ObservableList<ReserveTM> reserveTMS = FXCollections.observableArrayList();

        switch (newValue){
            case "Fully Paid" :
                reserveTMS = FXCollections.observableArrayList(reserveTbl.getItems()
                        .stream()
                        .filter(reserveTM -> reserveTM.getKeyMoney()-reserveTM.getPaidKeyMoney()==0)
                        .collect(Collectors.toList())
                );break;

            case "Half Paid" :
                reserveTMS = FXCollections.observableArrayList(reserveTbl.getItems()
                        .stream()
                        .filter(reserveTM -> reserveTM.getKeyMoney()-reserveTM.getPaidKeyMoney()>0)
                        .collect(Collectors.toList())
                );break;

            case "Not Paid" :
                reserveTMS = FXCollections.observableArrayList(reserveTbl.getItems()
                        .stream()
                        .filter(reserveTM -> reserveTM.getKeyMoney()-reserveTM.getPaidKeyMoney()==reserveTM.getKeyMoney())
                        .collect(Collectors.toList())
                );break;
        }
        reserveTbl.setItems(reserveTMS);
    }

    private void setCmbxPaymentStatusData() {
        cmbxPaymentStatus.setItems(FXCollections.observableArrayList("Fully Paid","Half Paid","Not Paid"));
    }

    private void setSelectedReservationData(ReserveTM newValue) {
        selectedReserveDTO = new ReserveDTO();

        Student student = new Student();
        student.setStudentId(newValue.getStudentId());

        Room room = new Room();
        room.setRoomId(newValue.getRoomId());

        selectedReserveDTO.setDate(newValue.getDate());
        selectedReserveDTO.setKeyMoney(newValue.getKeyMoney());
        selectedReserveDTO.setReserveId(newValue.getReserveId());
        selectedReserveDTO.setRoom(room);
        selectedReserveDTO.setReservationStatus(newValue.getStatus());
        selectedReserveDTO.setPaidKeyMoney(newValue.getPaidKeyMoney());
        selectedReserveDTO.setStudent(student);
    }

    private void setReservationTblData() throws Exception {
        Function<ReserveDTO, ReserveTM> function = (dto)->new ReserveTM(
                dto.getReserveId(),dto.getStudent().getStudentId(),dto.getRoom().getRoomId(),
                dto.getDate(),dto.getKeyMoney(),dto.getPaidKeyMoney(),(dto.getKeyMoney()-dto.getPaidKeyMoney()),dto.getReservationStatus()
        );
        reserveTbl.setItems(FXCollections.observableArrayList(dataConvertor.convert(reserveBO.getAllReservations(), function)));
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
            txtRoomKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
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

            //Update Room Status
            updateRoomStatus();

            new Alert(Alert.AlertType.CONFIRMATION,"Reservation Placed Successfully").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            e.printStackTrace();
        }
    }

    private void updateRoomStatus() {
        try {
            RoomDTO roomDTO = roomBO.getRoom(cmbxRoomIds.getSelectionModel().getSelectedItem());
            Room room = new Room(
                    roomDTO.getRoomId(),roomDTO.getType(),roomDTO.getMonthlyRental(),
                    "Not Available",null,roomDTO.getKeyMoney());

            roomBO.updateRoom(new RoomDTO(
                    room.getRoomId(),room.getType(),room.getMonthlyRental(),room.getAvailability(),
                    room.getKeyMoney()
            ));

        } catch (Exception e) {
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

    public void refreshCtxmOnAction(ActionEvent actionEvent) {
        try {
            setReservationTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCtxmOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Update-Reservation-Form.fxml"));

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Transfer Room Data to Update Form
        UpdateReservationFormController controller = fxmlLoader.getController();
        controller.setValuesForInputFields(selectedReserveDTO);

        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);

        Navigations.getInstance().transparentUi(stage,scene);
    }

    public void deleteCtxmOnAction(ActionEvent actionEvent) {
    }
}
