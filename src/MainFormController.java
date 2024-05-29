import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    public void btnSubmit(ActionEvent actionEvent) {
    }
    public void initialize(){
        HibernateUtil.getSession();
    }
}
