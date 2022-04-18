package gui.controller;

import be.Customer;
import gui.model.DeleteCustomerModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteCustomerController implements Initializable {

    @FXML
    private Button deleteBtn;

    @FXML
    private ChoiceBox<?> customerChoiceBox;

    DeleteCustomerModel deleteCustomerModel;

    public DeleteCustomerController(){

        deleteCustomerModel = new DeleteCustomerModel();

    }

    private void loadData(){

        customerChoiceBox.getItems().addAll(deleteCustomerModel.getAllCustomers());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }

    public void delete(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (customerChoiceBox.getValue() == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                deleteCustomerModel.deleteCustomer(((Customer) customerChoiceBox.getSelectionModel().getSelectedItem()).getEmail());
                JOptionPane.showMessageDialog(jFrame, "CUSTOMER DELETED !!");
                Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backBtnPressed(ActionEvent actionEvent) {

        Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            scene.setFill(Color.TRANSPARENT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
