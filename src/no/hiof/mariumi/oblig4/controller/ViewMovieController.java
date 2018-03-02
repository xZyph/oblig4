package no.hiof.mariumi.oblig4.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import no.hiof.mariumi.oblig4.ExecGui;
import no.hiof.mariumi.oblig4.model.Movie;
import no.hiof.mariumi.oblig4.model.Production;

import java.time.format.DateTimeFormatter;

public class ViewMovieController {
    public DatePicker idDatePicker;
    private boolean editMode = false;

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
    private TextField idMovieRuntime;
    @FXML
    private Button btnNewMovie;
    @FXML
    private Button btnEditMovie;
    @FXML
    private Button idDeleteMovie;
    @FXML
    private Button btnSave;

    @FXML
    public void initialize() {
        idMovieList.setItems(ExecGui.prodSys.getMovieList());
        idMovieList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Production>() {
            @Override
            public void changed(ObservableValue<? extends Production> observable, Production oldValue, Production newValue) {
                if(newValue != null)
                    updateInfo(newValue);
            }
        });
        idMovieList.getSelectionModel().select(0);

        if(!editMode) {
            btnSave.setVisible(false);

            idDatePicker.setOnMouseClicked(e -> {
                if (idDatePicker.isShowing())
                    idDatePicker.hide();
            });
        }
    }

    private void updateInfo(Production selectedProduction) {
        idMovieTitle.setText(selectedProduction.getTitle());
        idMovieDescription.setText(selectedProduction.getDescription());
        idDatePicker.setValue(selectedProduction.getReleaseDate());
        idMovieRuntime.setText(selectedProduction.getRuntime() + " min");
    }

    public void goToAddMovie(ActionEvent actionEvent) {
        ExecGui.prodSys.addMovie();
    }

    public void deleteMovie(ActionEvent actionEvent) {
        if(!idMovieList.getItems().isEmpty()){
            idMovieList.getItems().remove(idMovieList.getSelectionModel().getSelectedIndex());
        }
        else {
            ExecGui.prodSys.showError("Stop deleting stuff that isn't there man..");
        }
    }

    public void saveMovie(ActionEvent actionEvent) {

    }
}