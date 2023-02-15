package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;

import ba.unsa.etf.rpr.exceptions.MedicineException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class OnlineOrderController {
    public Button btnCancel;
    public Label medicineName;
    public Label medicinePrice;
    public Label medicineQuantity;
    public Label medicineDescription;

    private String email;
   // private  LoginController loginController= new LoginController();


    @FXML
    public void initialize()
    {
        medicineName.setText(MedicineController.medicine.getName());
        medicinePrice.setText(String.valueOf(MedicineController.medicine.getPrice()) + "$");
        medicineQuantity.setText(String.valueOf(MedicineController.medicine.getQuantity()));
        medicineDescription.setText(MedicineController.medicine.getDescription());

    }

    /*public void insertData(){
        String query2 = "INSERT INTO OnlineOrder  VALUES (?,?,?)";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPR_baza",
                    "freedb_smuratovic3", "JSkRP5Z5XgZ7T*a");
            PreparedStatement preparedStmt = connection.prepareStatement(query2);
            preparedStmt.setInt(1, 3);
            preparedStmt.setInt(2, MedicineController.medicine.getPrice());
            preparedStmt.setInt(3, UserManager.findEmail(loginController.getEmail()).getId());

            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MedicineException e) {
            throw new RuntimeException(e);
        }
    }

     */
    public void confirm(ActionEvent actionEvent) {
        try {
          //  insertData();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/confirmorder.fxml")));
            stage.setTitle("Confirm");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage =(Stage)btnCancel.getScene().getWindow();
        stage.close();
    }
}
