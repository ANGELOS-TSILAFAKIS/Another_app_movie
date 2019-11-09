package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Movies;

public interface OnGetMovieCallback {
    void onSuccess(Movies movie);
    void onError();
}
