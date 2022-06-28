/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/28/2022 7:27 AM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.SystemUserBO;
import lk.ijse.hms.dto.SystemUserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateUserDetailFormController implements Initializable {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXTextField txtNewUserName;
    public JFXTextField txtNewPassword;

    //DI
    SystemUserBO systemUserBO = (SystemUserBO) BOFactory.getInstance().getBO(BOFactory.BOType.SYSTEM_USER);

    public UpdateUserDetailFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
                systemUserBO.updateSystemUser(systemUserDTO);
                new Alert(Alert.AlertType.CONFIRMATION,"Updated Login Details").show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
