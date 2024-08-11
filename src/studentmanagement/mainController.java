/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package studentmanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tan
 */
public class mainController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private AnchorPane addPane;

    @FXML
    private Button addStudentsBtn;

    @FXML
    private Button coursesBtn;

    @FXML
    private Button gradesBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane homePane;

    @FXML
    void addStudentsBtnOnClick(ActionEvent event) {

    }

    @FXML
    void coursesBtnOnClick(ActionEvent event) {

    }

    @FXML
    void gradesBtnOnClick(ActionEvent event) {

    }

    @FXML
    void homeBtnOnClick(ActionEvent event) {

    }
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void checkLogin() {
        
        String sql = "SELECT * FROM account WHERE username = ? and password = ?";
        
        connect = connection.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            
            result = prepare.executeQuery();
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Invalid Login", "Username and password cannot be empty.");
            } else if (result.next()) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successfully", "Welcome, Admin!");

                loginBtn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.getIcons().add(new Image("assets/icon/graduated.png"));
                stage.setTitle("Student Management System");
                stage.setScene(scene);
                stage.show();

            } else {
                 showAlert(Alert.AlertType.ERROR, "Invalid Login", "Username or password is wrong.");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        };
    };
    
      @FXML
    private void keyPressed(KeyEvent keyEvent) {
    if (keyEvent.getCode() == KeyCode.ENTER) {
        loginBtn.fire();
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
