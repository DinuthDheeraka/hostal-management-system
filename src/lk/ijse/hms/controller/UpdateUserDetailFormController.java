/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/28/2022 7:27 AM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.SystemUserBO;
import lk.ijse.hms.dto.SystemUserDTO;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.util.RegexValidator;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UpdateUserDetailFormController implements Initializable {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXTextField txtNewUserName;
    public JFXTextField txtNewPassword;
    public JFXButton updateBtn;

    //DI
    SystemUserBO systemUserBO = (SystemUserBO) BOFactory.getInstance().getBO(BOFactory.BOType.SYSTEM_USER);

    public UpdateUserDetailFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateBtn.setDisable(true);
    }

    public void setCurrentLoginData(String userName,String password){
        txtUserName.setText(userName);
        txtPassword.setText(password);
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        try {

            //Update User
            SystemUserDTO systemUserDTO = systemUserBO.getSystemUserByUserNameAndPassword(txtUserName.getText(),txtPassword.getText());
            systemUserDTO.setUserName(txtNewUserName.getText());
            systemUserDTO.setPassword(txtNewPassword.getText());

            try {
                if(!systemUserBO.isPasswordExists(txtNewPassword.getText())){
                    systemUserBO.updateSystemUser(systemUserDTO);
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated Login Details").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Password Already Exists").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Unable to Update Login Details").show();
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cancelBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void validate(KeyEvent keyEvent) {
        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

        Pattern userName = Pattern.compile("[A-Za-z ]{3,10}");
        map.put(txtNewUserName,userName);

        Pattern password = Pattern.compile("[0-9A-Za-z {},*&^%#@!]{3,10}$");
        map.put(txtNewPassword,password);

        RegexValidator.validate(map,updateBtn);
    }
}
