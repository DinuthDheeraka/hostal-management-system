/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/25/2022 11:39 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.PaymentBO;
import lk.ijse.hms.bo.custom.ReserveBO;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.PaymentDTO;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.util.RegexValidator;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UpdatePaymentFormController implements Initializable {
    public JFXTextField txtPaymentId;
    public JFXComboBox<String> cmbxStudentIds;
    public JFXComboBox<String> cmbxReservationIds;
    public JFXTextField txtMonthlyRental;
    public JFXTextField txtPaidAmount;
    public JFXTextField txtMonth;
    public JFXButton updateBtn;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    ReserveBO reserveBO = (ReserveBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    public UpdatePaymentFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbxReservationIdsData();
        setCmbxStudentIdsData();
        updateBtn.setDisable(true);
        txtPaymentId.setEditable(false);
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        try {
            //Update Student
            Student student = new Student();
            student.setStudentId(cmbxStudentIds.getSelectionModel().getSelectedItem());

            //Update Payment
            PaymentDTO paymentDTO = paymentBO.getPayment(txtPaymentId.getText());
            paymentDTO.setMonth(txtMonth.getText());
            paymentDTO.setPaidAmount(Double.valueOf(txtPaidAmount.getText()));
            paymentDTO.setAmountToPay(Double.valueOf(txtMonthlyRental.getText()));
            paymentDTO.setReservationId(cmbxReservationIds.getSelectionModel().getSelectedItem());
            paymentDTO.setStudent(student);

            paymentBO.updatePayment(paymentDTO);

            new Alert(Alert.AlertType.CONFIRMATION,"Updated Payment Details").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Unable to Update Payment Details").show();
            e.printStackTrace();
        }
    }

    private void setCmbxReservationIdsData() {
        try {
            cmbxReservationIds.setItems(FXCollections.observableArrayList(reserveBO.getAllReservationIds()));
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

    public void cancelBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void setValuesForInputFields(PaymentDTO selectedPaymentDTO) {
        cmbxStudentIds.getSelectionModel().select(selectedPaymentDTO.getStudent().getStudentId());
        cmbxReservationIds.getSelectionModel().select(selectedPaymentDTO.getReservationId());
        txtPaymentId.setText(selectedPaymentDTO.getPaymentId());
        txtMonth.setText(selectedPaymentDTO.getMonth());
        txtMonthlyRental.setText(String.format("%.2f",selectedPaymentDTO.getAmountToPay()));
        txtPaidAmount.setText(String.format("%.2f",selectedPaymentDTO.getPaidAmount()));
    }

    public void validate(KeyEvent keyEvent) {

        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

        Pattern money = Pattern.compile("([0-9]{1,}.[0-9]{2}$|[0-9]{1,}$)");
        map.put(txtPaidAmount,money);

        map.put(txtMonthlyRental,money);

        Pattern month = Pattern.compile("[0-9]{4}-[0-9]{2}");
        map.put(txtMonth,month);

        RegexValidator.validate(map,updateBtn);
    }
}
