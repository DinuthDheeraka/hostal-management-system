/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/15/2022 11:02 PM
 */
package lk.ijse.hms.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
    public volatile boolean stop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDate();
        showTime();
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
