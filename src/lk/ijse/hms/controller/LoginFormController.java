/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/16/2022 1:34 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.SystemUserBO;
import lk.ijse.hms.dto.SystemUserDTO;
import lk.ijse.hms.util.IdsGenerator;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXTextField txtNewUserName;
    public JFXPasswordField txtNewPassword;
    public JFXTextField txtShowPassword;
    public ImageView imgHidePassword;
    public ImageView imgShowPassword;
    public JFXTextField txtSgnupShowPassword;
    public ImageView sgnupImgHidePassword;
    public ImageView sgnupImgShowPassword;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    //DI
    SystemUserBO systemUserBO = (SystemUserBO) BOFactory.getInstance().getBO(BOFactory.BOType.SYSTEM_USER);

    private String lastUserId;

    public LoginFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lastUserId = IdsGenerator.generateId("US-",systemUserBO.getLastUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void loginBtnOnAction(ActionEvent actionEvent) {
        try {
            if(systemUserBO.isExistsSystemUserByUserNameAndPassword(txtUserName.getText(),txtPassword.getText())){
                Navigations.getInstance().closeStage(actionEvent);
                //Navigations.getInstance().setNewStage("Main-Form");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Main-Form.fxml"));

                try {
                    parent = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Transfer User Data to Main Form
                MainFormController controller = fxmlLoader.getController();
                controller.setUserName(txtUserName.getText(),txtPassword.getText());

                stage = new Stage();
                scene = new Scene(parent);
                stage.setScene(scene);

                Navigations.getInstance().transparentUi(stage,scene);
            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid Username or Password!").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sgnpLoginBtnOnAction(ActionEvent actionEvent) {
        try {
            try {
                //Add New System User
                systemUserBO.addSystemUser(new SystemUserDTO(lastUserId,txtNewUserName.getText(),txtNewPassword.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sgnupCancelBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void sgnupShowPassword(MouseEvent mouseEvent) {
        sgnupImgShowPassword.setVisible(false);
        sgnupImgHidePassword.setVisible(true);

        txtNewPassword.setVisible(false);
        txtSgnupShowPassword.setText(txtNewPassword.getText());
        txtSgnupShowPassword.setVisible(true);
    }

    public void sgnupHidePassword(MouseEvent mouseEvent) {
        sgnupImgShowPassword.setVisible(true);
        sgnupImgHidePassword.setVisible(false);

        txtNewPassword.setVisible(true);
        txtNewPassword.setText(txtSgnupShowPassword.getText());
        txtSgnupShowPassword.setVisible(false);
    }

    public void hidePassword(MouseEvent mouseEvent) {
        imgShowPassword.setVisible(true);
        imgHidePassword.setVisible(false);

        txtPassword.setVisible(true);
        txtPassword.setText(txtShowPassword.getText());
        txtShowPassword.setVisible(false);
    }

    public void showPassword(MouseEvent mouseEvent) {
        imgShowPassword.setVisible(false);
        imgHidePassword.setVisible(true);

        txtShowPassword.setText(txtPassword.getText());
        txtShowPassword.setVisible(true);
        txtPassword.setVisible(false);
    }
}
