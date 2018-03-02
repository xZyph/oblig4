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

public class AddMovieController {

    @FXML
    private TextField idMovieTitle;
    @FXML
    private TextArea idMovieDescription;
    @FXML
    private DatePicker idDatePicker;
    @FXML
    private TextField idMovieRuntime;
    @FXML
    private Button btnDismiss;
    @FXML
    private Button btnSave;

    @FXML
    public void initialize() {
        idMovieRuntime.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue() > oldValue.intValue()){
                    char x = idMovieRuntime.getText().charAt(oldValue.intValue());
                    if(!(x >= '0' && x <= '9')){
                        idMovieRuntime.setText(idMovieRuntime.getText().substring(0, idMovieRuntime.getText().length()-1));
                    }
                }
                if(Integer.parseInt(idMovieRuntime.getText()) > 6000){
                    ExecGui.prodSys.showError("You seriously want me to believe that the movie is over 100 hours long? -_-");
                    idMovieRuntime.setText("1337");
                }
            }
        });
    }

    @FXML
    private void dismiss(ActionEvent actionEvent) {
        Stage stage = (Stage) btnDismiss.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveData(ActionEvent actionEvent) {
        try {
            if(idMovieTitle.getText().isEmpty())
                throw new Error("Dear sir/madam, the movie your are trying to add really needs a title...");
            if(idMovieDescription.getText().isEmpty())
                throw new Error("It is common courtesy to add a description when adding a movie.");
            if(idDatePicker.getValue() == null)
                throw new Error("You seriously forgot to try the awesome JavaFX DatePicker?!");
            if(Integer.parseInt(idMovieRuntime.getText()) < 0)
                throw new Error("I know it's confusing, but the runtime is measured in ints. I mean minutes!");

            Production newObj = new Movie(
                    idMovieTitle.getText(),
                    idMovieDescription.getText(),
                    idDatePicker.getValue(),
                    Integer.parseInt(idMovieRuntime.getText())
            );

            ExecGui.prodSys.getMovieList().add(newObj);

            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }
        catch (Error e) {
            ExecGui.prodSys.showError(e.getLocalizedMessage());
        }
    }
}
