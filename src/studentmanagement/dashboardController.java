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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import java.util.Date;
import javafx.beans.binding.Bindings;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
;

public class dashboardController implements Initializable {

    
    @FXML
    private AnchorPane addForm;

    @FXML
    private TableColumn<StudentData, Date> student_col_birth;

    @FXML
    private TableColumn<StudentData, String> student_col_major;

    @FXML
    private Button studentsBtn;

    @FXML
    private DatePicker student_birth;

    @FXML
    private TableColumn<StudentData, String> student_col_email;

    @FXML
    private TableColumn<StudentData, String> student_col_gender;

    @FXML
    private TableColumn<StudentData, String> student_col_name;

    @FXML
    private TableColumn<StudentData, String> student_col_phonenum;

    @FXML
    private TableColumn<StudentData, String> student_col_studentid;

    @FXML
    private TableColumn<StudentData, String> student_col_year;

    @FXML
    private TextField student_email;

    @FXML
    private ComboBox<String> student_gender;

    @FXML
    private TextField student_major;

    @FXML
    private TextField student_name;

    @FXML
    private TextField student_phonenum;

    @FXML
    private TextField student_search;

    @FXML
    private TextField student_studentid;

    @FXML
    private TableView<StudentData> student_tableView;

    @FXML
    private ComboBox<String> student_year;

    @FXML
    private Button coursesBtn;

    @FXML
    private AnchorPane coursesForm;

    @FXML
    private Button gradesBtn;

    @FXML
    private AnchorPane gradesForm;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private Button logOutBtn;
    
     @FXML
    private TextField course_courseid;

    @FXML
    private TextField course_name;

    @FXML
    private ComboBox<Integer> course_credits;
    
    @FXML
    private TextField course_grades;
    
    @FXML
    private TableView<Course> course_tableView;

    @FXML
    private TableColumn<Course, String> course_col_courseid;

    @FXML
    private TableColumn<Course, String> course_col_name;

    @FXML
    private TableColumn<Course, Integer> course_col_credits;
    
    @FXML
    private TableColumn<Course, Double> course_col_grades;


    public void studentYearList() {
        ObservableList<String> yearOptions = FXCollections.observableArrayList("First Year", "Second Year", "Third Year", "Fourth Year");
        student_year.setItems(yearOptions);       
    }
        
   
    public void studentGenderList() {
        ObservableList<String> genderOptions = FXCollections.observableArrayList("Female", "Male");
        student_gender.setItems(genderOptions);
    };
    
    
    
     public void addCourseDegreeList() {
        ObservableList<Integer> creditsOptions = FXCollections.observableArrayList(1,2,3,4);
        course_credits.setItems(creditsOptions);
    };
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    @FXML
    private Label home_totalStudent;

    public void homeDisplayTotaledStudents() {
        String sql = "SELECT COUNT(sid) FROM student";
        connect = connection.connectDb();
        int counted = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                counted = result.getInt("COUNT(sid)");
            }
            home_totalStudent.setText(String.valueOf(counted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private Label home_totalFemale;
   
     public void homeDisplayFemale() {
        String sql = "SELECT COUNT(sid) FROM student WHERE gender = 'Female'";
        connect = connection.connectDb();
        try {
            int countFemale = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                countFemale = result.getInt("COUNT(sid)");
            }
            home_totalFemale.setText(String.valueOf(countFemale));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label home_totalMale;
    
    public void homeDisplayMale() {
        String sql = "SELECT COUNT(sid) FROM student WHERE gender = 'Male'";
        connect = connection.connectDb();
        try {
            int countMale = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                countMale = result.getInt("COUNT(sid)");
            }
            home_totalMale.setText(String.valueOf(countMale));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private Label home_avgMarks;
    
    @FXML
    private Label home_graduationClassification;
    
    public void homeDisplayAvgMarks() {

        String sql = "{CALL allStudentAvgGrade()}";

        connect = connection.connectDb();

        try {
 
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                Double avgGrade = result.getDouble("AverageGrade");
                String formattedAvgGrade = String.format("%.2f", avgGrade);
                home_avgMarks.setText(formattedAvgGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }      
    
    @FXML
    private Label home_totalCourses;

    public void homeDisplayTotaledCourses() {
        String sql = "SELECT COUNT(cid) FROM course";
        connect = connection.connectDb();
        int counted = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                counted = result.getInt("COUNT(cid)");
            }
            home_totalCourses.setText(String.valueOf(counted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private Label home_passRate;
    public void homeDisplayPassRate() {
        String sql = "{Call passRate()}";
        connect = connection.connectDb();
        try{
            prepare  = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if(result.next()) {
                Double passRate = result.getDouble("PassRate");
                String formattedPassRate = String.format("%.2f", passRate*100) + "%";
                home_passRate.setText(formattedPassRate);
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();;
        }
    }
    
 @FXML
    private PieChart genderChart;

    public void homeDisplayGenderChart() {
        genderChart.getData().clear();
        String sql = "{CALL genderCounts()}";
        connect = connection.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            while (result.next()) {              
                int maleStudentCount = result.getInt("MaleCount");
                int femaleStudentCount = result.getInt("FemaleCount");
                PieChart.Data maleData = new PieChart.Data("Male", maleStudentCount);
                PieChart.Data femaleData = new PieChart.Data("Female", femaleStudentCount);
                pieChartData.addAll(maleData,femaleData);
                pieChartData.forEach(data -> 
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName() + ": " + data.getPieValue())));
            }
            genderChart.getData().addAll(pieChartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    private BarChart<String,Double> gradeChart;
   
    
    public void homeDisplayGradeChart() {
        gradeChart.getData().clear();
        String sql = "SELECT COUNT(grade),grade FROM student_course GROUP BY grade";
        connect = connection.connectDb();
        try {                  
            prepare = connect.prepareCall(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                XYChart.Series<String, Double> series = new XYChart.Series<>();
                String name = String.valueOf(result.getDouble("grade"));
                String count = String.valueOf(result.getInt("COUNT(grade)"));
                double grade = result.getDouble("grade");
                series.getData().add(new XYChart.Data(count,grade));
                series.setName(name);
                gradeChart.getData().add(series);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        
    
    @FXML
    public void logOut() throws IOException {
            Alert alert = new Alert(AlertType.CONFIRMATION,"Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                
                logOutBtn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }
        } 
    
    public boolean studentIsEmpty() {
        if(student_studentid.getText().isEmpty()
                    || student_name.getText().isEmpty()                    
                    || student_phonenum.getText().isEmpty()
                    || student_email.getText().isEmpty()
                    || student_year.getSelectionModel().getSelectedItem() == null
                    || student_birth.getValue() == null
                    || student_gender.getSelectionModel().getSelectedItem() == null
                    || student_major.getText().isEmpty())
            return true;
        return false;
    }
    @FXML
    public void studentAdd() {
        String insertData = "{CALL studentAdd(?, ?, ?, ?, ?, ?, ?, ?)}";
        connect = connection.connectDb();
        try {
            if (studentIsEmpty())
            {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
            } else {
                String checkData = "SELECT sid FROM student WHERE sid = '"
                        + student_studentid.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                if (result.next()) {
                    showAlert(AlertType.ERROR, "Error Message", "Student #" + student_studentid.getText() + " was already exist!");
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, student_studentid.getText());                    
                    prepare.setString(2, student_name.getText());
                    prepare.setString(3, student_phonenum.getText());
                    prepare.setString(4, student_email.getText());
                    prepare.setString(5, (String) student_year.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(student_birth.getValue()));
                    prepare.setString(7, (String) student_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(8, student_major.getText());                 
                    prepare.executeUpdate();                                                                                         
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Added!");         
                    studentShowListData();
                    studentClear();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public ObservableList<StudentData> studentListData() {
        ObservableList<StudentData> listStudents = FXCollections.observableArrayList();
        String sql = "SELECT * FROM student";
        connect = connection.connectDb();
        try {
            StudentData studentdata;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                listStudents.add(new StudentData(result.getString("sid"),
                        result.getString("sname"),
                        result.getString("phonenumber"),
                        result.getString("email"),
                        result.getString("yearofstudy"),
                        result.getDate("birth"),
                        result.getString("gender"),
                        result.getString("major")));               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStudents;
    }
    
    private ObservableList<StudentData> studentListData;
    
    public void studentShowListData() {
        student_col_studentid.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        student_col_name.setCellValueFactory(new PropertyValueFactory<>("studentname"));
        student_col_phonenum.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        student_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        student_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        student_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        student_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        student_col_major.setCellValueFactory(new PropertyValueFactory<>("major"));
        studentListData = studentListData();
        student_tableView.setItems(studentListData);
    }
    private <T> void selectComboBoxItem(ComboBox<T> comboBox, T item) {
        if (item != null) {
        ObservableList<T> items = comboBox.getItems();
        comboBox.getSelectionModel().select(items.indexOf(item));
        }
    }
    
    @FXML
    public void studentSelect() {
        StudentData studentData = student_tableView.getSelectionModel().getSelectedItem();       
        if (studentData == null) {
            return;
        }
            student_studentid.setText(studentData.getStudentid());
            student_name.setText(studentData.getStudentname());
            student_birth.setValue(studentData.getBirth().toLocalDate()); // Assuming Birth is a Date
            student_phonenum.setText(studentData.getPhonenumber());
            student_email.setText(studentData.getEmail());
            selectComboBoxItem(student_year, studentData.getYear());
            selectComboBoxItem(student_gender, studentData.getGender());
            student_major.setText(studentData.getMajor());
    }
    
    public void studentClear() {
        student_studentid.setText("");
        student_name.setText("");
        student_phonenum.setText("");
        student_email.setText("");
        student_year.getSelectionModel().clearSelection();
        student_birth.setValue(null);
        student_gender.getSelectionModel().clearSelection();
        student_major.setText("");
    }
        
        
    @FXML
    public void studentDelete() {
        String deleteData = "DELETE FROM student WHERE sid = '"
                + student_studentid.getText() + "'";
        connect = connection.connectDb();
        try {        
            if (student_studentid.getText().isEmpty())
            {
                showAlert(AlertType.ERROR, "Error Message", "Please fill Student ID"); 
            } else {     
                Alert alert = new Alert(AlertType.CONFIRMATION,"Are you sure you want to DELETE Student #" + student_studentid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {              
                    String checkData = "SELECT sid FROM student_course "
                            + "WHERE sid = '" + student_studentid.getText() + "'";
                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();
                    if (result.next()) {
                        String deleteGrade = "DELETE FROM student_course WHERE "
                                + "sid = '" + student_studentid.getText() + "'";
                        statement = connect.createStatement();
                        statement.executeUpdate(deleteGrade);
                    }                      
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Deleted!");                  
                    studentShowListData();
                    studentClear();
                }
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    public void studentUpdate() {
        String updateData = "{CALL UpdateStudent(?, ?, ?, ?, ?, ?, ?, ?)}";
        connect = connection.connectDb();
        try {
            if (studentIsEmpty()) 
            {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");                   
            } else {                
                Alert alert = new Alert(AlertType.CONFIRMATION,"Are you sure you want to UPDATE Student #" + student_studentid.getText() + "?");   
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, student_studentid.getText());                    
                    prepare.setString(2, student_name.getText());
                    prepare.setString(3, student_phonenum.getText());
                    prepare.setString(4, student_email.getText());
                    prepare.setString(5, (String) student_year.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(student_birth.getValue()));
                    prepare.setString(7, (String) student_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(8, student_major.getText());                 
                    prepare.executeUpdate();                         
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Updated!");                        
                    studentShowListData();
                    studentClear();
                } else {
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    public void studentSearch() {
        FilteredList<StudentData> filter = new FilteredList<>(studentListData, e -> true);
        student_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateStudentData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }          
                String searchKey = newValue.toLowerCase();                          
                if (predicateStudentData.getStudentid().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStudentname().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getPhonenumber().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getMajor().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<StudentData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(student_tableView.comparatorProperty());
        student_tableView.setItems(sortList);
    }
    
    private boolean courseisEmpty() {
        if(course_courseid.getText().isEmpty()
                    || course_name.getText().isEmpty()
                    || course_credits.getSelectionModel().getSelectedItem() == null)
            return true;
        return false;
    }
    
    
    @FXML
    public void courseAdd() {
        String insertData = "{CALL courseAdd(?, ?, ?)}";
        connect = connection.connectDb();
        try {
            if (courseisEmpty())                    
            {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");                    
            } else {
                String checkData = "SELECT cid FROM course WHERE cid = '"
                    + course_courseid.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                if (result.next()) {
                    showAlert(AlertType.ERROR, "Error Message", "Course: " + course_courseid.getText() + " was already exist!");                         
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, course_courseid.getText());
                    prepare.setString(2, course_name.getText());
                    prepare.setString(3, course_credits.getSelectionModel().getSelectedItem().toString());
                    prepare.executeUpdate();                    
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Added!");                      
                    courseShowListData();
                    courseClear();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void courseUpdate() {
        String updateData = "UPDATE course SET cname = '"
                + course_name.getText() + "', credits = '"
                + course_credits.getSelectionModel().getSelectedItem() + "' WHERE cid = '"
                + course_courseid.getText() + "'";
        connect = connection.connectDb();

        try {          
            if (courseisEmpty())                    
            {             
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to UPDATE Course: " + course_courseid.getText() + "?");               
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);                   
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Updated!");                    
                    courseShowListData();
                    courseClear();
                } else {
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void courseDelete() {
        String deleteData = "DELETE FROM course WHERE cid = '"
                + course_courseid.getText() + "'";
        connect = connection.connectDb();
        try {
            if (course_courseid.getText().isEmpty()) 
            {
                showAlert(AlertType.ERROR, "Error Message", "Please fill Course ID" + course_courseid.getText());                
            } else {              
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to DELETE Course: " + course_courseid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {                                      
                    String checkData = "SELECT cid FROM student_course "
                            + "WHERE cid = '" + course_courseid.getText() + "'";
                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();
                    if (result.next()) {
                        String deleteGrade = "DELETE FROM student_course WHERE "
                                + "cid = '" + course_courseid.getText() + "'";
                        statement = connect.createStatement();
                        statement.executeUpdate(deleteGrade);
                    }             
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Deleted!");  
                    courseShowListData();
                    courseClear();
                } else {
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void courseSelect() {
        Course courseData = course_tableView.getSelectionModel().getSelectedItem();
        if (courseData == null) {
            return;
        }
        course_courseid.setText(courseData.getCourseid());
        course_name.setText(courseData.getCoursename());
        course_credits.setValue(courseData.getCredits());
    }

    @FXML
    public void courseClear() {
        course_courseid.setText("");
        course_name.setText("");
        course_credits.setValue(null);
    }

    public ObservableList<Course> courseListData() {
        ObservableList<Course> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM course";
        connect = connection.connectDb();
        try {
            Course courseData;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                courseData = new Course(result.getString("cid"),
                        result.getString("cname"),
                        result.getInt("credits"));
                listData.add(courseData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Course> courseList;

    public void courseShowListData() {
        courseList = courseListData();
        course_col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        course_col_name.setCellValueFactory(new PropertyValueFactory<>("coursename"));
        course_col_credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        course_tableView.setItems(courseList);
    }
    
    @FXML
    TextField grades_grade;
    
    @FXML
    ComboBox<String> grades_courseid;
    
    @FXML
    public void courseIDList() {
        String listCourseID = "SELECT * FROM course";
        connect = connection.connectDb();
        try {
            ObservableList listCourseData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listCourseID);
            result = prepare.executeQuery();
            while (result.next()) {
                listCourseData.add(result.getString("cid"));
            }
            grades_courseid.setItems(listCourseData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    ComboBox<String> grades_studentid;
    
    @FXML
    public void studentIDList() {
        String listStudentID = "SELECT * FROM student";
        connect = connection.connectDb();
        try {
            ObservableList listStudentData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listStudentID);
            result = prepare.executeQuery();
            while (result.next()) {
                listStudentData.add(result.getString("sid"));
            }
            grades_studentid.setItems(listStudentData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean gradesAddisEmpty() {
        if(grades_studentid.getSelectionModel().getSelectedItem() == null
                    || grades_courseid.getSelectionModel().getSelectedItem() == null
                    || grades_grade.getText().isEmpty())
            return true;
        return false;
    }
       
    @FXML
    public void gradesAdd() {
        String insertData = "INSERT INTO student_course (sid,cid,grade) VALUES(?,?,?)";
        connect = connection.connectDb();
        try {
            if (gradesAddisEmpty())                    
            {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");                    
            } else {
                String checkData = "SELECT sid,cid FROM student_course WHERE sid = '"
                        + grades_studentid.getSelectionModel().getSelectedItem() + 
                        "' and cid = '" + grades_courseid.getSelectionModel().getSelectedItem() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    showAlert(AlertType.ERROR, "Error Message", "Field was already exist!");                         
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, grades_studentid.getSelectionModel().getSelectedItem());
                    prepare.setString(2, grades_courseid.getSelectionModel().getSelectedItem());
                    prepare.setString(3, grades_grade.getText());
                    prepare.executeUpdate();                   
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Added!");                      
                    gradesShowListData();
                    gradesClear();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private TableView<StudentCourse> grades_tableView;
    
    @FXML
    private TableColumn<StudentData, String> grades_col_studentid;
            
    @FXML
    private TableColumn<StudentData, String> grades_col_courseid;
    
    @FXML
    private TableColumn<StudentData, String> grades_col_grade;
    
    @FXML
    private ObservableList<StudentCourse> gradesList;
    
    public void gradesShowListData() {
        gradesList = gradesListData();
        grades_col_studentid.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        grades_col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        grades_col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        grades_tableView.setItems(gradesList);
    }
    
    public ObservableList<StudentCourse> gradesListData() {
        ObservableList<StudentCourse> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM student_course";
        connect = connection.connectDb();
        try {
            StudentCourse studentData;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                studentData = new StudentCourse(result.getString("sid"),
                         result.getString("cid"),
                         result.getDouble("grade"));
                listData.add(studentData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }   
    
    @FXML
    public void gradesUpdate() {
        String checkData = "SELECT * FROM student_course WHERE sid = '"
                + grades_studentid.getSelectionModel().getSelectedItem() + "' && cid = '"
                + grades_courseid.getSelectionModel().getSelectedItem() + "'";
        connect = connection.connectDb();
        try {
            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();     
            if (gradesAddisEmpty())                    
            {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");  
            } else {
                if(result.next()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to UPDATE Student #" + grades_studentid.getSelectionModel().getSelectedItem() + "?");                  
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)) {
                        String updateData = "UPDATE student_course SET "
                            + " grade = '" + grades_grade.getText()
                            + "' WHERE sid = '"
                            + grades_studentid.getSelectionModel().getSelectedItem() + "' && cid = '"
                            + grades_courseid.getSelectionModel().getSelectedItem() + "'";
                        statement = connect.createStatement();
                        statement.executeUpdate(updateData);
                        showAlert(AlertType.INFORMATION, "Information Message", "Successfully Updated!");                    
                        gradesShowListData();
                        gradesClear();
                    }                  
                } else {
                    showAlert(AlertType.ERROR, "Error Message", "Not Found!");   
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void gradesClear() {
        grades_studentid.getSelectionModel().clearSelection();
        grades_courseid.getSelectionModel().clearSelection();
        grades_grade.setText("");     
    }  
    
    @FXML
    public void gradesSelect() {
        StudentCourse grades = grades_tableView.getSelectionModel().getSelectedItem();
        if (grades == null) {
            return;
        }       
        grades_studentid.setValue(grades.getStudentid());
        grades_courseid.setValue(grades.getCourseid());
        grades_grade.setText(String.valueOf(grades.getGrade()));
    }

    @FXML
    TextField grades_search;
    
    @FXML
    public void gradesSearch() {
        FilteredList<StudentCourse> filter = new FilteredList<>(gradesList, e -> true);
        grades_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateStudentData -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }              
                String searchKey = newValue.toLowerCase();
                if (predicateStudentData.getStudentid().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourseid().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGrade().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<StudentCourse> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(grades_tableView.comparatorProperty());
        grades_tableView.setItems(sortList);
    }
    
    @FXML
    public void gradesDelete() {
        int index = grades_tableView.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            showAlert(AlertType.ERROR, "Error Message", "Please select a row to delete.");
            return;
        }
        String selectedStudentId = grades_studentid.getSelectionModel().getSelectedItem();
        String selectedCourseId = grades_courseid.getSelectionModel().getSelectedItem();
        String columnStudentId = grades_col_studentid.getCellData(index);
        String columnCourseId = grades_col_courseid.getCellData(index);
        String deleteData = "DELETE FROM student_course WHERE sid = '"
                + grades_studentid.getSelectionModel().getSelectedItem()  + "' && cid = '"
                + grades_courseid.getSelectionModel().getSelectedItem() + "'";
        connect = connection.connectDb();
        try {
            if (!selectedStudentId.equalsIgnoreCase(columnStudentId) || !selectedCourseId.equalsIgnoreCase(columnCourseId))
            {
                showAlert(AlertType.ERROR, "Error Message", "Not Found!");
            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to DELETE This Field: ?");               
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);               
                    showAlert(AlertType.INFORMATION, "Information Message", "Successfully Deleted!");                
                    gradesShowListData();
                    gradesClear();
                } else {   
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void switchForm(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            homeForm.setVisible(true);
            addForm.setVisible(false);
            coursesForm.setVisible(false);
            gradesForm.setVisible(false);
            homeBtn.setStyle("-fx-background-color:#109EDF ;");
            studentsBtn.setStyle("-fx-background-color:transparent");
            coursesBtn.setStyle("-fx-background-color:transparent");
            gradesBtn.setStyle("-fx-background-color:transparent");
            homeDisplayAvgMarks();
            homeDisplayTotaledStudents();
            homeDisplayMale();
            homeDisplayFemale();      
            homeDisplayPassRate();
            homeDisplayGenderChart();
            homeDisplayGradeChart();
            homeDisplayTotaledCourses();
        } else if (event.getSource() == studentsBtn) {
            homeForm.setVisible(false);
            addForm.setVisible(true);
            coursesForm.setVisible(false);
            gradesForm.setVisible(false);
            homeBtn.setStyle("-fx-background-color:transparent;");
            studentsBtn.setStyle("-fx-background-color:#109EDF ;");
            coursesBtn.setStyle("-fx-background-color:transparent");
            gradesBtn.setStyle("-fx-background-color:transparent");
            studentYearList();
            studentGenderList();
            courseIDList();
            studentIDList();
            studentShowListData();
            studentSearch();
        } else if (event.getSource() == coursesBtn) {
            homeForm.setVisible(false);
            addForm.setVisible(false);
            coursesForm.setVisible(true);
            gradesForm.setVisible(false);
            homeBtn.setStyle("-fx-background-color:transparent");
            studentsBtn.setStyle(" -fx-background-color:transparent");
            coursesBtn.setStyle("-fx-background-color:#109EDF;");
            gradesBtn.setStyle("-fx-background-color:transparent");
            courseShowListData();          
        } else if (event.getSource() == gradesBtn) {
            homeForm.setVisible(false);
            addForm.setVisible(false);
            coursesForm.setVisible(false);
            gradesForm.setVisible(true);
            homeBtn.setStyle("-fx-background-color:transparent");
            studentsBtn.setStyle(" -fx-background-color:transparent");
            coursesBtn.setStyle("-fx-background-color:transparent");
            gradesBtn.setStyle("-fx-background-color:#109EDF;");
            gradesShowListData();
            gradesSearch();
        }
    }       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentYearList();
        studentGenderList();
        addCourseDegreeList();
        studentShowListData();
        homeDisplayAvgMarks();
        homeDisplayTotaledStudents();
        homeDisplayMale();
        homeDisplayFemale();
        homeDisplayPassRate();
        homeDisplayTotaledCourses();
        homeDisplayGradeChart();
        homeDisplayGenderChart();
        courseShowListData();
        courseIDList();
        studentIDList();
        gradesShowListData(); 
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
