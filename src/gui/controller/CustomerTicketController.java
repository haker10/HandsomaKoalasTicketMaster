package gui.controller;

import gui.model.CustomerTicketModel;
import javafx.application.Platform;
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
    private Label EventNameLBL;

    @FXML
    private Label uRLLBL;

    @FXML
    private Label startDateNTimeLBL;

    @FXML
    private Label endDateNTimeLbl;

    @FXML
    private Label additionalInfo;

    @FXML
    private Label addressLBL;

    @FXML
    private Label CPhoneLBL;

    @FXML
    private Label CNameLBL;

    @FXML
    private Label CEmailLBL;

    @FXML
    private Label ticketTypes;

    @FXML
    private Label Label1;


    @FXML
    private AnchorPane anchorPane;

    CustomerTicketModel customerTicketModel;

    public CustomerTicketController() {
        customerTicketModel = new CustomerTicketModel();
    }

    public void loadData() {
        Platform.runLater(() -> {
            customerTicketModel = new CustomerTicketModel();
            Stage currentStage = (Stage) ticketTypes.getScene().getWindow();
            String ticket = (String) currentStage.getUserData();
            String ticketT [] = ticket.split("\n\n");

            EventNameLBL.setText(ticketT[0]);
            ticketTypes.setText(ticketT[1]);
            startDateNTimeLBL.setText(ticketT[2]);
            endDateNTimeLbl.setText(ticketT[3]);
            addressLBL.setText(ticketT[4]);
            uRLLBL.setText(ticketT[5]);
            additionalInfo.setText(ticketT[6]);
            CNameLBL.setText(ticketT[7]);
            CEmailLBL.setText(ticketT[8]);
            CPhoneLBL.setText(ticketT[9]);

            String customerName = ticketT[6];
            String eventName = ticketT[0];
            String customerNameFinal [] = customerName.split(" ");
            String eventNameFinal [] = eventName.split(" ");
            String customerNameFinal2 = eventNameFinal[2] + customerNameFinal[2] + "ticket.png";

            try {
                WritableImage image = anchorPane.snapshot(new SnapshotParameters(), null);
                File file = new File(String.format("src/images/tickets/%s", customerNameFinal2));
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
