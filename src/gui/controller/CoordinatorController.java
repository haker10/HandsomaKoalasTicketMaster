package gui.controller;

import gui.model.AdminModel;
import gui.model.CoordinatorModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorController implements Initializable {

    @FXML
    private Label coordinatorPageLbl;

    CoordinatorModel coordinatorModel;

    public CoordinatorController(){

        coordinatorModel = new CoordinatorModel();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
