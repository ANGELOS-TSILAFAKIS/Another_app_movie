package eu.artandroidapps.mvvm_tmdb.moviesapp.ui;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Movies;

public interface MoviesAdapterOnClickHandler {
    void addToFavourites(Movies movie);
    void movieDetails(Movies movie);
}
