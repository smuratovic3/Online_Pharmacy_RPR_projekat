package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MedicineManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Medicine;
import ba.unsa.etf.rpr.exceptions.MedicineException;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


public class MedicineController {
    @FXML
    public TableView<Medicine> medicineTable;
    public ObservableList<Medicine> lista = FXCollections.observableArrayList();
    public TextField searchText;
    private final MedicineManager medicineManager = new MedicineManager();
    private final UserManager  userManager = new UserManager();




    @FXML
     public TableColumn<Medicine, String> nameColumn  = new TableColumn<>();
    @FXML
    public TableColumn<Medicine, Integer> priceColumn  = new TableColumn<>();
    @FXML
     public TableColumn<Medicine, Integer>  quantityColumn  = new TableColumn<>();
    @FXML
     public TableColumn<Medicine, String>  descriptionColumn  = new TableColumn<>();
    @FXML
    public TableColumn<Medicine, Integer> orderColumn;



    @FXML
    public void initialize()
{
         nameColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
         priceColumn.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("price"));
         quantityColumn.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("quantity"));
         descriptionColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));
        class OrderCell extends TableCell<Medicine, Integer> {
        private Button btn = new Button("Order");
        public OrderCell() {
            btn.setOnAction(event -> {
                Medicine medicine = getTableView().getItems().get(getIndex());
                // Add your logic here to handle the button click event
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/aboutus.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Log in");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
            });
        }
        @Override
        protected void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(btn);
            }
        }
    }
    orderColumn.setCellFactory(column -> new OrderCell());



    refreshMedicine();
}


    private void viewButtonOnAction(int trainId) {
        
    }

    void refreshMedicine() {
        try {
            medicineTable.setItems(FXCollections.observableList(medicineManager.getAll()));
            medicineTable.refresh();
        } catch (MedicineException e) {
            e.printStackTrace();
        }
    }
    public void searchByText(ActionEvent actionEvent)
    {
        try {
            List<Medicine> lista =medicineManager.searchMedicine(searchText.getText());
            if(lista.size() > 0){
                System.out.println(lista.get(0).getName() + " " + lista.get(0).getDescription());
            }
            medicineTable.setItems(FXCollections.observableList(lista));
        } catch (MedicineException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
