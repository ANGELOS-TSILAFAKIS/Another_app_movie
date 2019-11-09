package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Genres;

import java.util.List;

public interface OnGetGenresCallback {
    void onSuccess(List<Genres> genres);
    void onError();
}
