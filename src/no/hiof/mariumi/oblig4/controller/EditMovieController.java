package no.hiof.mariumi.oblig4.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.hiof.mariumi.oblig4.ExecGui;
import no.hiof.mariumi.oblig4.customcomponents.numberfield.NumberField;
import no.hiof.mariumi.oblig4.model.Production;

public class EditMovieController {

    // GUI Elements
    @FXML
    private ListView<Production> movieList;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker releaseDate;
    @FXML
    private NumberField runtime;
    @FXML
    private Button btnSave;

    @FXML
    public void initialize() {
        // Updating fields based on item chosen.
        movieList.setItems(ExecGui.prodSys.getMovieList());

        // Selecting first item automatically, unless there is no items.
        if(!movieList.getItems().isEmpty()) {
            movieList.getSelectionModel().select(ExecGui.prodSys.selectedItem);
        }
        else {
            ExecGui.prodSys.viewMovies();
            ExecGui.prodSys.showError("Something when wrong with the list...");
        }

        updateInfo(movieList.getSelectionModel().getSelectedItem());
    }

    private void updateInfo(Production selectedProduction) {
        titleLabel.setText(selectedProduction.getTitle());
        title.setText(selectedProduction.getTitle());
        description.setText(selectedProduction.getDescription());
        releaseDate.setValue(selectedProduction.getReleaseDate());
        runtime.setText(Integer.toString(selectedProduction.getRuntime()));
    }

    /**
     * Exiting editmode and storing data.
     */
    public void saveMovie() {
        updateData();
        ExecGui.prodSys.viewMovies();
    }

    public void updateData() {
        movieList.getSelectionModel().getSelectedItem().setTitle(title.getText());
        movieList.getSelectionModel().getSelectedItem().setDescription(description.getText());
        movieList.getSelectionModel().getSelectedItem().setReleaseDate(releaseDate.getValue());
        movieList.getSelectionModel().getSelectedItem().setRuntime(Integer.parseInt(runtime.getText()));
    }
}
