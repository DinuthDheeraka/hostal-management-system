/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/21/2022 7:35 PM
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.JoinQueryBO;
import lk.ijse.hms.bo.custom.PaymentBO;
import lk.ijse.hms.bo.custom.ReserveBO;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.PaymentDTO;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.service.DataConvertor;
import lk.ijse.hms.util.IdsGenerator;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.view.tdm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaymentFormController implements Initializable {
    public JFXTextField txtMonth;
    public JFXComboBox<String> cmbxStudentIds;
    public JFXTextField txtPaymentId;
    public JFXComboBox<String> cmbxRservationIds;
    public JFXTextField txtPaidAmount;
    public JFXTextField txtMonthlyRental;
    public TableView<PaymentTM> paymentTbl;
    public TableColumn colPaymentId;
    public TableColumn colStudentId;
    public TableColumn colReservationId;
    public TableColumn colDate;
    public TableColumn colMonth;
    public TableColumn colMonthlyRental;
    public TableColumn colPaidAmount;
    public TableColumn colAmountToPay;
    public TextField txtSearchBar;
    public JFXComboBox<String> cmbxPaymentStatus;
    public JFXComboBox<String> cmbxFiltersMonths;
    public JFXComboBox<String> cmbxFiltersYear;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    //Selected Payment Details
    PaymentDTO selectedPaymentDTO = new PaymentDTO();

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    JoinQueryBO joinQueryBO = (JoinQueryBO) BOFactory.getInstance().getBO(BOFactory.BOType.JOIN_QUERY);
    DataConvertor dataConvertor = DataConvertor.getInstance();

    public PaymentFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory("paymentId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory("studentId"));
        colReservationId.setCellValueFactory(new PropertyValueFactory("reservationId"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colMonth.setCellValueFactory(new PropertyValueFactory("month"));
        colMonthlyRental.setCellValueFactory(new PropertyValueFactory("amountToPay"));
        colPaidAmount.setCellValueFactory(new PropertyValueFactory("paidAmount"));
        colAmountToPay.setCellValueFactory(new PropertyValueFactory("balance"));

        setCmbxStudentIdsData();
        setCmbxReservationIdsData();
        setGeneratedId();
        setPaymentTblData();
        setCmbxFilterMonthsData();
        setCmbxFilterYearsData();
        setCmbxPaymentStatusData();

        cmbxRservationIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setReservationDataToTextFileds(newValue);
                    }
                }
        );

        cmbxFiltersYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setFilterByYearPaymentData(newValue);
                    }
                }
        );

        cmbxFiltersMonths.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setFilterByMonthPaymentData(newValue);
                    }
                }
        );

        cmbxPaymentStatus.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setFilterByPaymentStatusData(newValue);
                    }
                }
        );

        paymentTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedPaymentData(newValue);
                });

        try {
            txtPaymentId.setText(IdsGenerator.generateId("PM-",paymentBO.getLastPaymentId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFilterByPaymentStatusData(String newValue) {
        ObservableList<PaymentTM> observableList = FXCollections.observableArrayList();

        switch (newValue){
            case  "Fully Paid" :
                observableList = FXCollections.observableArrayList(
                        paymentTbl.getItems().stream()
                                .filter(paymentTM -> paymentTM.getBalance()==0)
                                .collect(Collectors.toList())
                );break;

            case "Half Paid" :
                observableList = FXCollections.observableArrayList(
                        paymentTbl.getItems().stream()
                                .filter(paymentTM -> paymentTM.getPaidAmount()>0)
                                .collect(Collectors.toList())
                );break;
        }
        paymentTbl.setItems(observableList);
    }

    private void setFilterByMonthPaymentData(String newValue) {
        ObservableList<PaymentTM> observableList = FXCollections.observableArrayList(
                paymentTbl.getItems().stream()
                        .filter(paymentTM -> paymentTM.getMonth().endsWith("-"+cmbxFiltersMonths.getSelectionModel().getSelectedItem()))
                        .collect(Collectors.toList())
        );
        paymentTbl.setItems(observableList);
    }

    private void setFilterByYearPaymentData(String newValue) {
        ObservableList<PaymentTM> observableList = FXCollections.observableArrayList(
                paymentTbl.getItems().stream()
                        .filter(paymentTM -> paymentTM.getMonth().startsWith(cmbxFiltersYear.getSelectionModel().getSelectedItem()))
                        .collect(Collectors.toList())
        );
        paymentTbl.setItems(observableList);
    }

    private void setCmbxFilterYearsData() {
        ArrayList<String> years = new ArrayList();
        for(int i = 2020; i<=LocalDate.now().getYear(); i++){
            years.add(String.valueOf(i));
        }
        cmbxFiltersYear.setItems(FXCollections.observableArrayList(years));
    }

    private void setCmbxPaymentStatusData() {
        cmbxPaymentStatus.setItems(FXCollections.observableArrayList("Fully Paid","Half Paid"));
    }

    private void setCmbxFilterMonthsData() {
        ArrayList<String> months = new ArrayList();
        for(int i = 1; i<=12; i++){
            months.add(String.format("%02d",i));
        }
        cmbxFiltersMonths.setItems(FXCollections.observableArrayList(months));
    }

    private void setSelectedPaymentData(PaymentTM newValue) {
        Student student = new Student();
        student.setStudentId(newValue.getStudentId());

        selectedPaymentDTO.setReservationId(newValue.getReservationId());
        selectedPaymentDTO.setStudent(student);
        selectedPaymentDTO.setPaymentId(newValue.getPaymentId());
        selectedPaymentDTO.setAmountToPay(newValue.getAmountToPay());
        selectedPaymentDTO.setPaidAmount(newValue.getPaidAmount());
        selectedPaymentDTO.setMonth(newValue.getMonth());
    }

    private ObservableList<PaymentTM> setPaymentTblData(){
        Function<PaymentDTO,PaymentTM> function = (dto)->new PaymentTM(
                dto.getPaymentId(), dto.getDate(),dto.getMonth(),dto.getAmountToPay(),
                dto.getPaidAmount(),dto.getAmountToPay()-dto.getPaidAmount(),dto.getReservationId(),
                dto.getStudent().getStudentId()
        );
        try {
            paymentTbl.setItems(FXCollections.observableArrayList(dataConvertor.convert(paymentBO.getAllPayments(),function)));
            return FXCollections.observableArrayList(dataConvertor.convert(paymentBO.getAllPayments(),function));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setReservationDataToTextFileds(String newValue) {
        try {
            txtMonthlyRental.setText(String.valueOf(joinQueryBO.getRoomMonthlyRentalByReservationId(newValue).getMonthlyRental()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCmbxReservationIdsData() {
        try {
            cmbxRservationIds.setItems(FXCollections.observableArrayList(reserveBO.getAllReservationIds()));
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

    public void canselBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makePaymentBtnOnAction(ActionEvent actionEvent) {
        //Getting Student
        Student student = new Student();
        student.setStudentId(cmbxStudentIds.getSelectionModel().getSelectedItem());

        //Make Payment
        try {
            paymentBO.addPayment(new PaymentDTO(
                    txtPaymentId.getText(), null,txtMonth.getText(),Double.valueOf(txtMonthlyRental.getText()),Double.valueOf(txtPaidAmount.getText()),
                    cmbxRservationIds.getSelectionModel().getSelectedItem(), student
            ));
            setGeneratedId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGeneratedId(){
        try {
            txtPaymentId.setText(IdsGenerator.generateId("PM-", paymentBO.getLastPaymentId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshCtxmOnAction(ActionEvent actionEvent) {
        setPaymentTblData();
    }

    public void updateCtxmOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Update-Payment-Form.fxml"));

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Transfer Room Data to Update Form
        UpdatePaymentFormController controller = fxmlLoader.getController();
        controller.setValuesForInputFields(selectedPaymentDTO);

        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);

        Navigations.getInstance().transparentUi(stage,scene);
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) throws Exception {
        ObservableList<PaymentTM> list = setPaymentTblData();

        ObservableList<PaymentTM> observableList = FXCollections.observableArrayList();

        if(txtSearchBar.getText().startsWith("PM-")){
            //Filter by Payment-Id
            observableList = FXCollections.observableArrayList(
                    list.stream()
                            .filter(paymentDTO -> paymentDTO.getPaymentId().contains(txtSearchBar.getText()))
                            .collect(Collectors.toList())
            );
        }else if(txtSearchBar.getText().startsWith("ST-")){
            //Filter by Student-Id
            observableList = FXCollections.observableArrayList(
                    list.stream()
                            .filter(paymentTM -> paymentTM.getStudentId().contains(txtSearchBar.getText()))
                            .collect(Collectors.toList())
            );
        }else if(txtSearchBar.getText().startsWith("RS-")){
            //Filter by Reservation-Id
            observableList = FXCollections.observableArrayList(
                    list.stream()
                            .filter(paymentTM -> paymentTM.getReservationId().contains(txtSearchBar.getText()))
                            .collect(Collectors.toList())
            );
        }
        paymentTbl.setItems(observableList);
    }
}
