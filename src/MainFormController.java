import entity.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MainFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TableView tblStudent;
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
                loadall();
                new Alert(Alert.AlertType.INFORMATION,"Updated......!").show();
            }
        }
    }

    private void loadall() {
        try(Session session = HibernateUtil.getSession()){
            List<Student> selectedStudent = session.createQuery("FROM Student").list();
            for(Student tempStudent : selectedStudent){

            }
        }
    }

    public void initialize(){
        HibernateUtil.getSession();
    }
}
