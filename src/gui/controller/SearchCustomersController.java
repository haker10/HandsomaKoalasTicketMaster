package gui.controller;

import be.Customer;
import gui.model.SearchCustomersModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchCustomersController implements Initializable {

    @FXML
    private Button checkCustomerBtn;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView<Customer> customersTableView;

    @FXML
    private TableColumn nameColumn;

    @FXML
    private TableColumn emailColumn;

    @FXML
    private TableColumn phoneColumn;


    SearchCustomersModel searchCustomersModel;

    public SearchCustomersController(){

        searchCustomersModel = new SearchCustomersModel();

    }

    public void updatePlayListTableView() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        try {
            customersTableView.setItems(searchCustomersModel.getAllCustomers());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePlayListTableView();
        ObservableList<Customer> customersList = searchCustomersModel.getAllCustomers();
        FilteredList<Customer> filteredData = null;
        try {
            filteredData = new FilteredList<>(customersList, b -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }



        FilteredList<Customer> finalFilteredData = filteredData;
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredData.setPredicate(customer -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getName().toLowerCase().contains(lowerCaseFilter) || customer.getEmail().toLowerCase().contains(lowerCaseFilter) || customer.getPhone().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else
                    return false;
            });
        });


        SortedList<Customer> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(customersTableView.comparatorProperty());

        customersTableView.setItems(sortedData);
    }

    public void checkCustomer(ActionEvent actionEvent) {
        TablePosition pos = customersTableView.getSelectionModel().getSelectedCells().get(0);
        String customerEmail = (String) emailColumn.getCellObservableValue((customersTableView.getItems().get(pos.getRow()))).getValue();
        Stage currentStage = (Stage) checkCustomerBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/customerParticipationView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.setUserData(customerEmail);
            stage.show();
            scene.setFill(Color.TRANSPARENT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
