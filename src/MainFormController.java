import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;

import java.util.List;

public class MainFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TableView<StudentTM> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDelete;
    public TableColumn colUpdate;
    public Button btn;

    public void btnSubmit(ActionEvent actionEvent) {
        Student student = new Student(Long.parseLong(txtId.getText()),txtName.getText(),txtAddress.getText());
        if (btn.getText().equals("Save Student")){
            //save
            try(Session session = HibernateUtil.getSession()){
                Transaction transaction = session.beginTransaction();
                session.save(student);
                transaction.commit();
                loadall();
                new Alert(Alert.AlertType.INFORMATION,"Saved.....!").show();
            }
        }else{
            //update
            try(Session session = HibernateUtil.getSession()){
                Transaction transaction = session.beginTransaction();
                Student selectedStudent = session.get(Student.class, student.getId());
                if (selectedStudent==null){
                    new Alert(Alert.AlertType.WARNING,"Student not found....!");
                    return;
                }
                selectedStudent.setName(student.getName());
                selectedStudent.setAddress(student.getAddress());

                transaction.commit();
                btn.setText("Save Student");
                loadall();
                new Alert(Alert.AlertType.INFORMATION,"Updated......!").show();
            }
        }
    }

    private void loadall() {
        try(Session session = HibernateUtil.getSession()){
            ObservableList<StudentTM> tms= FXCollections.observableArrayList();
            List<Student> selectedStudent = session.createQuery("FROM Student").list();
            for(Student tempStudent : selectedStudent){
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                tms.add(new StudentTM(tempStudent.getId(), tempStudent.getName(), tempStudent.getAddress(), deleteButton,updateButton));

                deleteButton.setOnAction(e->{
                    try(Session deleteSession = HibernateUtil.getSession()){
                        Transaction deleteTransaction = deleteSession.beginTransaction();
                        //Student student = deleteSession.find(Student.class, tempStudent.getId());
                        deleteSession.delete(tempStudent);
                        deleteTransaction.commit();

                        loadall();
                        new Alert(Alert.AlertType.INFORMATION,"Student deleted");
                    }
                });
                updateButton.setOnAction(e->{
                    txtId.setText(String.valueOf(tempStudent.getId()));
                    txtName.setText(tempStudent.getName());
                    txtAddress.setText(tempStudent.getAddress());
                    btn.setText("Update Student");
                });
            }
            tblStudent.setItems(tms);
        }
    }

    public void initialize(){
        HibernateUtil.getSession();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        loadall();
    }
}
