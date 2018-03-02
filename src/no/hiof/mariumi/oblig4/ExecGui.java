package no.hiof.mariumi.oblig4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.hiof.mariumi.oblig4.model.Movie;
import no.hiof.mariumi.oblig4.model.Production;
import java.io.IOException;
import java.time.LocalDate;

/**
 * ITF10611 - OOP
 * Oblig 4
 *
 * @author Marius Selvfölgelig Mikelsen
 * @version 0.1
 *
 * TODO:
 *   - Add functionality for LocalDate input in viewMovies.fxml
 *   - Demonstrate all non-graphic assignments in code.
 *   - Clean up code.
 *   - Add remaining JavaDoc.
 */
public class ExecGui extends Application{
    public static ExecGui prodSys;
    private Stage primaryStage;
    private ObservableList<Production> movieList = FXCollections.observableArrayList();

    public ExecGui() {
        prodSys = this;

        movieList.add(new Movie(
                "Serenity",
                "The crew of the ship Serenity try to evade an assassin sent to recapture one of their members who is telepathic.",
                LocalDate.of(2005, 10, 30),
                119
        ));

        movieList.add(new Movie(
                "The Fifth Element",
                "In the colorful future, a cab driver unwittingly becomes the central figure in the search for a legendary cosmic weapon to keep Evil and Mr. Zorg at bay.",
                LocalDate.of(1997, 5, 9),
                126
        ));

        movieList.add(new Movie(
                "Inception",
                "A thief, who steals corporate secrets through the use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a CEO.",
                LocalDate.of(2010, 7, 16),
                148
        ));

        movieList.add(new Movie(
                "The Matrix",
                "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                LocalDate.of(1999, 3, 31),
                136
        ));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        movieOverview();
    }

    public void movieOverview() {
        Parent viewMoviesFXML = null;

        try {
            viewMoviesFXML = FXMLLoader.load(getClass().getResource("view/ViewMovies.fxml"));
        }
        catch (IOException e) {
            showError(e.getMessage());
        }

        Scene viewMovies = new Scene(viewMoviesFXML);
        primaryStage.setScene(viewMovies);
        primaryStage.setTitle("Movie Overview");
        primaryStage.show();
    }

    public void addMovie() {
        Parent addMovieFXML = null;

        try {
            addMovieFXML = FXMLLoader.load(getClass().getResource("view/AddMovie.fxml"));
        }
        catch (IOException e) {
            showError(e.getMessage());
        }

        Stage newMovie = new Stage();
        Scene addMovie = new Scene(addMovieFXML);
        newMovie.setScene(addMovie);
        newMovie.setAlwaysOnTop(true);
        newMovie.initOwner(primaryStage);
        newMovie.initModality(Modality.WINDOW_MODAL);
        newMovie.showAndWait();
    }

    private void showError(String msg) {
        Alert e = new Alert(Alert.AlertType.ERROR);
        e.setTitle("Error");
        e.setHeaderText(null);
        e.setContentText(msg);
        e.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /* GETTERS */
    public ObservableList<Production> getMovieList() {
        return movieList;
    }
}
