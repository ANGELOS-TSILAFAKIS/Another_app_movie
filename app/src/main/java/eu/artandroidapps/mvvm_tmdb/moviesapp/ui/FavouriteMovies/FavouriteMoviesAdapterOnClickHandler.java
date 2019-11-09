package eu.artandroidapps.mvvm_tmdb.moviesapp.ui.FavouriteMovies;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Movies;

public interface FavouriteMoviesAdapterOnClickHandler {
    void removeFromFavourites(Movies movie);
    void movieDetails(Movies movie);
}
