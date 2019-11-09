package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Reviews;

import java.util.List;

public interface OnGetReviewsCallback {
    void onSuccess(List<Reviews> reviews);
    void onError();
}
