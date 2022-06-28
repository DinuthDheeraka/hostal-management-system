/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/29/2022 4:11 AM
 */
package lk.ijse.hms.util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegexValidator {

    public static void validate(LinkedHashMap<TextField, Pattern> map, JFXButton btn) {
        boolean isDisale = false;
        for(TextField textField : map.keySet()){
            Pattern pattern = map.get(textField);
            if((!textField.getText().isEmpty())){
                if((!pattern.matcher(textField.getText()).matches())){
                    textField.setStyle("-fx-text-fill: red");
                    isDisale=true;
                }else{
                    textField.setStyle("-fx-text-fill: black");
                }
            }else{
                isDisale = true;
            }
        }
        disableOrEnablebtn(isDisale,btn);
    }

    private static void disableOrEnablebtn(boolean isDisale,JFXButton btn){
        if(isDisale){
            btn.setDisable(true);
        }else{
            btn.setDisable(false);
        }
    }
}
