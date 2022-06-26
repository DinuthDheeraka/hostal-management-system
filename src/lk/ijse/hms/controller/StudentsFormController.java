/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/17/2022 5:58 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.service.DataConvertor;
import lk.ijse.hms.util.Navigations;
import lk.ijse.hms.view.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StudentsFormController implements Initializable {
    public TableView<StudentTM> studentTbl;
    public TableColumn colStdId;
    public TableColumn colStdName;
    public TableColumn colStdDOB;
    public TableColumn colStdContactNo;
    public TableColumn colStdAddress;
    public TableColumn colStdCity;
    public TableColumn colStdDistrict;
    public TableColumn colStdProvince;
    public TableColumn colStdGender;
    public TableColumn colStdJoinedDate;
    public TextField txtSearchBar;
    public JFXButton addStudentBtn;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    //selected Student
    StudentDTO selectedStudent;

    //DI
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    DataConvertor dataConvertor = DataConvertor.getInstance();

    public StudentsFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStdId.setCellValueFactory(new PropertyValueFactory("studentId"));
        colStdName.setCellValueFactory(new PropertyValueFactory("name"));
        colStdGender.setCellValueFactory(new PropertyValueFactory("gender"));
        colStdDOB.setCellValueFactory(new PropertyValueFactory("DOB"));
        colStdContactNo.setCellValueFactory(new PropertyValueFactory("contactNo"));
        colStdAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colStdCity.setCellValueFactory(new PropertyValueFactory("city"));
        colStdDistrict.setCellValueFactory(new PropertyValueFactory("district"));
        colStdProvince.setCellValueFactory(new PropertyValueFactory("province"));
        colStdJoinedDate.setCellValueFactory(new PropertyValueFactory("joinedDate"));
        try {
            setStudentTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedStudentData(newValue);
                });

        controlAddStudentBtn();
    }

    private void controlAddStudentBtn() {
        if (studentTbl.getItems().size() < 125) {
            addStudentBtn.setDisable(false);
        } else {
            addStudentBtn.setDisable(true);
        }
    }

    private void setSelectedStudentData(StudentTM newValue) {
        selectedStudent = new StudentDTO(
                newValue.getStudentId(),newValue.getName(),newValue.getAddress(),
                newValue.getCity(),newValue.getDistrict(),newValue.getProvince(),newValue.getContactNo(),
                newValue.getDOB(),newValue.getGender(),newValue.getJoinedDate());
    }

    private void setStudentTblData() throws Exception {
        Function<StudentDTO,StudentTM> function = (dto)->
            new StudentTM(
                    dto.getStudentId(),dto.getName(),dto.getDob(),dto.getContactNo(),
                    dto.getAddress(),dto.getCity(),dto.getDistrict(), dto.getProvince(),
                    dto.getGender(),dto.getJoinedDate()
            );
        studentTbl.setItems(FXCollections.observableArrayList(dataConvertor.convert(studentBO.getAllStudents(),function)));
    }

    public void backToHomeBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().setNewStage("Add-Student-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshCtxmOnAction(ActionEvent actionEvent) {
        try {
            setStudentTblData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controlAddStudentBtn();
    }

    public void updateCtxmOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Add-Student-Form.fxml"));

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Transfer Room Data to Update Form
        AddStudentFormController controller = fxmlLoader.getController();
        controller.setValuesForInputFields(selectedStudent);

        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);

        Navigations.getInstance().transparentUi(stage,scene);
    }

    public void deleteCtxmOnAction(ActionEvent actionEvent) {
        try {
            studentBO.deleteStudent(selectedStudent.getStudentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();

        if(txtSearchBar.getText().startsWith("ST-")){
            //Search by Student Id
            studentTMS = FXCollections.observableArrayList(
                    studentTbl.getItems().stream()
                            .filter(studentTM -> studentTM.getStudentId().equals(txtSearchBar.getText()))
                            .collect(Collectors.toList())
            );
        }else{
            //Search by Student Name
            studentTMS = FXCollections.observableArrayList(
                    studentTbl.getItems().stream()
                            .filter(studentTM -> studentTM.getName().contains(txtSearchBar.getText()))
                            .collect(Collectors.toList())
            );
        }
        studentTbl.setItems(studentTMS);
    }
}
