package no.hiof.mariumi.oblig4.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import no.hiof.mariumi.oblig4.ExecGui;
import no.hiof.mariumi.oblig4.model.Movie;
import no.hiof.mariumi.oblig4.model.Production;

import java.time.LocalDate;

public class AddMovieController {
    @FXML
    private TextField idMovieTitle;
    @FXML
    private TextArea idMovieDescription;
    @FXML
    private TextField idMovieReleasedate;
    @FXML
    private TextField idMovieRuntime;
    @FXML
    private Button btnDismiss;
    @FXML
    private Button btnSave;

    @FXML
    private void dismiss(ActionEvent actionEvent) {
        Stage stage = (Stage) btnDismiss.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveData(ActionEvent actionEvent) {
        Production newObj = new Movie(
                idMovieTitle.getText(),
                idMovieDescription.getText(),
                LocalDate.now(),
                Integer.parseInt(idMovieRuntime.getText())
        );

        ExecGui.prodSys.getMovieList().add(newObj);

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
}
