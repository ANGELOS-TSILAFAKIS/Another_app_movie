package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

public interface OnGetTotalResultsCallback{
        void onSuccess(int totalResults);
        void onError();
}
