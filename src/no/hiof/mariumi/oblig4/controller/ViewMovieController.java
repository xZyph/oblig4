package no.hiof.mariumi.oblig4.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.hiof.mariumi.oblig4.ExecGui;
import no.hiof.mariumi.oblig4.model.Production;

import java.time.format.DateTimeFormatter;

public class ViewMovieController {
    // Standards
    DateTimeFormatter stdDate = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    // GUI Elements
    @FXML
    private ListView<Production> idMovieList;
    @FXML
    private Label idMovieTitle;
    @FXML
    private TextArea idMovieDescription;
    @FXML
    private TextField idMovieReleasedate;
    @FXML
    private TextField idMovieRuntime;
    @FXML
    private Button btnNewMovie;
    @FXML
    private Button btnEditMovie;
    @FXML
    private Button idDeleteMovie;

    @FXML
    public void initialize() {
        idMovieList.setItems(ExecGui.prodSys.getMovieList());
        idMovieList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Production>() {
            @Override
            public void changed(ObservableValue<? extends Production> observable, Production oldValue, Production newValue) {
                updateInfo(newValue);
            }
        });
    }

    private void updateInfo(Production selectedProduction) {
        idMovieTitle.setText(selectedProduction.getTitle());
        idMovieDescription.setText(selectedProduction.getDescription());
        idMovieReleasedate.setText(selectedProduction.getReleaseDate().format(stdDate));
        idMovieRuntime.setText(selectedProduction.getRuntime() + " min");
    }

    public void goToAddMovie(ActionEvent actionEvent) {
        ExecGui.prodSys.addMovie();
    }

    public void deleteMovie(ActionEvent actionEvent) {
        idMovieList.getItems().remove(idMovieList.getSelectionModel().getSelectedIndex());
    }
}