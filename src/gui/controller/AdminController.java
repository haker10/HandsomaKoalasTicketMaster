package gui.controller;

import gui.model.AdminModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Label adminPageLbl;

    AdminModel adminModel;

    public AdminController(){

        adminModel = new AdminModel();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
