package gui.controller;

import be.Customer;
import gui.model.CustomerTicketModel;
import gui.model.ListOfParticipantsModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerTicketController implements Initializable {

    @FXML
    private Label ticketLbl;

    @FXML
    private AnchorPane anchorPane;

    CustomerTicketModel customerTicketModel;

    public CustomerTicketController() {
        customerTicketModel = new CustomerTicketModel();
    }

    public void loadData() {
        Platform.runLater(() -> {
            customerTicketModel = new CustomerTicketModel();
            Stage currentStage = (Stage) ticketLbl.getScene().getWindow();
            String ticket = (String) currentStage.getUserData();
            ticketLbl.setText(ticket);
            String ticketT [] = ticket.split("\n\n");
            String customerName = ticketT[6];
            String eventName = ticketT[0];
            String customerNameFinal [] = customerName.split(" ");
            String eventNameFinal [] = eventName.split(" ");
            String customerNameFinal2 = eventNameFinal[2] + customerNameFinal[2] + "ticket.png";

            try {
                WritableImage image = anchorPane.snapshot(new SnapshotParameters(), null);
                File file = new File(String.format("C:\\Users\\Diogo\\Desktop\\%s", customerNameFinal2));
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });



    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }
}
