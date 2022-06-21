/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/21/2022 7:35 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.JoinQueryBO;
import lk.ijse.hms.bo.custom.PaymentBO;
import lk.ijse.hms.bo.custom.ReserveBO;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.PaymentDTO;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {
    public JFXTextField txtMonth;
    public JFXComboBox<String> cmbxStudentIds;
    public JFXTextField txtPaymentId;
    public JFXComboBox<String> cmbxRservationIds;
    public JFXTextField txtPaidAmount;

    private double amountToPay;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    JoinQueryBO joinQueryBO = (JoinQueryBO) BOFactory.getInstance().getBO(BOFactory.BOType.JOIN_QUERY);

    public PaymentFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbxStudentIdsData();
        setCmbxReservationIdsData();

        cmbxRservationIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setReservationDataToTextFileds(newValue);
                    }
                }
        );
    }

    private void setReservationDataToTextFileds(String newValue) {
        try {
            amountToPay =  joinQueryBO.getRoomMonthlyRentalByReservationId(newValue).getMonthlyRental();
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
                    txtPaymentId.getText(), null,txtMonth.getText(),amountToPay,Double.valueOf(txtPaidAmount.getText()),
                    cmbxRservationIds.getSelectionModel().getSelectedItem(), student
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
