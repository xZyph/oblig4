package no.hiof.mariumi.oblig4.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Born from the bones of the abstract Production class, we get the Movie class.
 * This class represents all movies, in the conventional sense.
 *
 * @author Marius Selvfölgelig Mikelsen
 * @version 0.1
 */
public class Movie extends Production implements Comparable<Movie> {

    /* CONSTRUCTORS */

    /**
     * Creates a generic movie object, including random runtime.
     */
    public Movie() {
        super("N/A", ThreadLocalRandom.current().nextInt(80, 220 + 1));
    }

    /**
     * Creates a movie object with title and runtime provided.
     *
     * @param title of movie.
     * @param runtime Length of movie in minutes.
     */
    public Movie(String title, int runtime) {
        super(title, runtime);
    }

    public Movie(String title, String description, LocalDate releaseDate, int runtime) {
        super(title, description, releaseDate, runtime);
    }

    /* GETTERS */

    /* SETTERS */

    /* OVERRIDES */

    @Override
    public String toString() {
        return getTitle() + " - " + (getRuntime() / 60) + "hrs " + (getRuntime() % 60) + "min";
    }

    @Override
    public int compareTo(Movie o) {
        return getTitle().toUpperCase().compareTo(o.getTitle().toUpperCase());
    }
}
