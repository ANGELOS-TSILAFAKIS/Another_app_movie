package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Trailers;

import java.util.List;

public interface OnGetTrailersCallback {
    void onSuccess(List<Trailers> trailers);
    void onError();
}
