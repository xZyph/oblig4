package no.hiof.mariumi.oblig4.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.hiof.mariumi.oblig4.ExecGui;
import no.hiof.mariumi.oblig4.customcomponents.numberfield.NumberField;
import no.hiof.mariumi.oblig4.model.Production;

import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class ViewMovieController {
    public DatePicker idDatePicker;
    private boolean editMode = false;
    private int selectedItem = 0;

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
    private NumberField idMovieRuntime;
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
        // Updating fields based on item chosen.
        idMovieList.setItems(ExecGui.prodSys.getMovieList());
        idMovieList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Production>() {
            @Override
            public void changed(ObservableValue<? extends Production> observable, Production oldValue, Production newValue) {
                if(newValue != null) {
                    selectedItem = idMovieList.getSelectionModel().getSelectedIndex();
                    updateInfo(newValue);
                }
            }
        });

        // Selecting first item automatically, unless there is no items.
        if(!idMovieList.getItems().isEmpty()) {
            idMovieList.getSelectionModel().select(selectedItem);
        }

        if(!editMode) {
            disableEditMode();
        }
    }

    /**
     * Updates relevant information
     * @param selectedProduction Any Production object
     */
    private void updateInfo(Production selectedProduction) {
        idMovieTitle.setText(selectedProduction.getTitle());
        idMovieDescription.setText(selectedProduction.getDescription());
        idDatePicker.setValue(selectedProduction.getReleaseDate());
        idMovieRuntime.setText(Integer.toString(selectedProduction.getRuntime()));
    }

    /**
     * Opens addMovie window
     * @param actionEvent
     */
    public void goToAddMovie(ActionEvent actionEvent) {
        ExecGui.prodSys.addMovie();
    }

    /**
     * Deletes selected movie
     * @param actionEvent
     */
    public void deleteMovie(ActionEvent actionEvent) {
        if(!idMovieList.getItems().isEmpty()){
            idMovieList.getItems().remove(idMovieList.getSelectionModel().getSelectedIndex());
        }
        else {
            ExecGui.prodSys.showError("Stop deleting stuff that isn't there man..");
        }
    }

    /**
     * Pretty self-explanatory.. Enables editMode and restarts GUI.
     */
    public void enableEditMode() {
        editMode = true;
        btnSave.setVisible(true);
        selectedItem = idMovieList.getSelectionModel().getSelectedIndex();
        idMovieDescription.editableProperty().setValue(true);
        idMovieRuntime.editableProperty().setValue(true);
        idDatePicker.editableProperty().setValue(true);
        idDatePicker.setOnMouseClicked(e -> {
            idDatePicker.show();
        });

        idMovieRuntime.setText(Integer.toString(idMovieList.getSelectionModel().getSelectedItem().getRuntime()));

        initialize();
    }

    public void disableEditMode() {
        btnSave.setVisible(false);

        idMovieDescription.editableProperty().setValue(false);
        idMovieRuntime.editableProperty().setValue(false);
        idDatePicker.editableProperty().setValue(false);

        idDatePicker.setOnMouseClicked(e -> {
            if (idDatePicker.isShowing())
                idDatePicker.hide();
        });
    }

    /**
     * Exiting editmode and storing data.
     */
    public void saveMovie() {
        idMovieList.getSelectionModel().getSelectedItem().setDescription(idMovieDescription.getText());
        idMovieList.getSelectionModel().getSelectedItem().setReleaseDate(idDatePicker.getValue());
        idMovieList.getSelectionModel().getSelectedItem().setRuntime(Integer.parseInt(idMovieRuntime.getText()));
        idMovieList.refresh();

        editMode = false;
        initialize();
    }
}