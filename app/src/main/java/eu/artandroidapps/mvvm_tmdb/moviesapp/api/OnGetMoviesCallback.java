package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Movies;

import java.util.List;

public interface OnGetMoviesCallback {
    void onSuccess(List<Movies> movies);
    void onError();
}
