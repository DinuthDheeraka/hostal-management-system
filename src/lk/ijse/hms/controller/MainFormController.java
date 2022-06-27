/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:02 PM
 */
package lk.ijse.hms.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane mainFormContext;
    public Label lblSystemTime;
    public Label lblSystemDate;
    public LineChart lineChrtIncomeStatus;
    public PieChart pieChrtKeyMoneyStatus;
    public LineChart lineChrtStudentJoiningStatus;

    public volatile boolean stop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDate();
        showTime();
        setPieChartKeyMoneyStatusData();
    }

    private void setPieChartKeyMoneyStatusData() {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(" Paid Students", 10),
                new PieChart.Data(" Unpaid Students",20));

        pieChrtKeyMoneyStatus.setTitle("Key money status for all Reservations");
        pieChrtKeyMoneyStatus.setData(pieChartData);
    }

    public void studentsBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().addParentToCurrentStage("Students-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void roomBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().addParentToCurrentStage("Rooms-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rservationBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().addParentToCurrentStage("Make-Reservation-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paymentBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().addParentToCurrentStage("Payment-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTime(){
        Thread thread = new Thread(()->{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while(!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timeNow = simpleDateFormat.format(new Date());
                Platform.runLater(()->{
                    lblSystemTime.setText(timeNow);
                });
            }
        });
        thread.start();
    }

    public void showDate(){
        lblSystemDate.setText(String.valueOf(LocalDate.now()));
    }

}
