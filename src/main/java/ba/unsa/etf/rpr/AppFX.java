package ba.unsa.etf.rpr;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AppFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/pocetna.fxml"));
        primaryStage.setTitle("Online Pharmacy");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) throws SQLException {
       Connection connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPR_baza",
                "freedb_smuratovic3", "JSkRP5Z5XgZ7T*a");
        System.out.println("Connected to the database");

        String query = "SELECT * FROM Medicine";
        try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    Integer id = rs.getInt(1);
                    String name = rs.getString(2);
                    System.out.println(id + " " + name);

                }
        } catch (SQLException e) {
            e.printStackTrace();
        }



/*
      String query2 = "INSERT INTO Medicine  VALUES (?,?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query2);
            preparedStmt.setInt(1, 10);
            preparedStmt.setString(2, "Analgin");
            preparedStmt.setInt(3, 10);
            preparedStmt.setInt(4, 100);
            preparedStmt.setString(5, "Good for a headache");
            preparedStmt.setInt(6, 1);

            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


*/



         launch(args);
    }
}
