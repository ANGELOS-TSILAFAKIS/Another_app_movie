package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

public interface OnGetTotalPagesCallback {
    void onSuccess(int totalPages);
    void onError();
}
