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
import lk.ijse.hms.util.NavigateUI;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
            NavigateUI.getNavigateUI().addParentToCurrentStage("Students-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void roomBtnOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().addParentToCurrentStage("Rooms-Form",mainFormContext);
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
        String pattern = "dd MMMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("En", "SL"));
        String date = simpleDateFormat.format(new Date());
        lblSystemDate.setText(date);
    }
}
