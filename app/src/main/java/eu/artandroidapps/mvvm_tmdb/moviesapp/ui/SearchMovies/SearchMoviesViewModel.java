package eu.artandroidapps.mvvm_tmdb.moviesapp.ui.SearchMovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetGenresCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetMoviesCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetTotalPagesCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetTotalResultsCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.TheMovieDbService;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Genres;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Movies;
import eu.artandroidapps.mvvm_tmdb.moviesapp.repository.MoviesRepositoryDb;
import eu.artandroidapps.mvvm_tmdb.moviesapp.repository.MoviesRepositoryApi;
import eu.artandroidapps.mvvm_tmdb.moviesapp.utils.CheckSettings;

import java.util.ArrayList;
import java.util.List;

public class SearchMoviesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Movies>> mMovies = new MutableLiveData<>();
    private MutableLiveData<List<Genres>> mGenres  = new MutableLiveData<>();
    private MutableLiveData<Integer> mLastPosition = new MutableLiveData<>();

    private MoviesRepositoryApi moviesRepositoryApi;
    private MoviesRepositoryDb moviesRepositoryDb;
    private CheckSettings checkSettings;
    private TheMovieDbService api;
    int currentSearchPage = 1;
    int totalResultsFromQuery,totalPagesFromResult;

    public SearchMoviesViewModel(Application application){
        super(application);
        checkSettings = checkSettings.getInstance(application);
        moviesRepositoryApi = new MoviesRepositoryApi(application,api,checkSettings.getLANGUAGE());
        moviesRepositoryDb = new MoviesRepositoryDb(application);
    }

    public void getSearchMovies(String query){
        moviesRepositoryApi = MoviesRepositoryApi.getInstance(getApplication());
        moviesRepositoryApi.getSearchMovies(query,new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movies> movies) {
                if(movies != null)
                    mMovies.postValue(movies);
            }

            @Override
            public void onError() {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to fetch movies");
            }
        });
        moviesRepositoryApi.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genres> genres) {
                if(genres != null)
                    mGenres.postValue(genres);
            }

            @Override
            public void onError() {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to fetch genres");
            }
        });

        moviesRepositoryApi.getSearchMoviesTotalPages(query, new OnGetTotalPagesCallback() {
            @Override
            public void onSuccess(int totalResults) {
                totalPagesFromResult = totalResults;
            }

            @Override
            public void onError() {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to fetch total pages");
            }
        });

        moviesRepositoryApi.getSearchMoviesTotalResults(query, new OnGetTotalResultsCallback() {
            @Override
            public void onSuccess(int totalResults) {
                totalResultsFromQuery = totalResults;
            }

            @Override
            public void onError() {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to fetch total pages");
            }
        });

    }

    public void getSearchMoviesNextPage(String query){
        currentSearchPage++;
        if(!(totalPagesFromResult == currentSearchPage))
        moviesRepositoryApi = MoviesRepositoryApi.getInstance(getApplication());
        moviesRepositoryApi.getSearchMoviesNextPage(query,currentSearchPage,new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movies> movies) {
                if(movies != null) {
                    List<Movies> newList = new ArrayList<Movies>(getmMovies().getValue());
                    newList.addAll(movies);
                    mMovies.postValue(newList);
                }
            }

            @Override
            public void onError() {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to fetch movies");
            }
        });
    }

    public LiveData<List<Movies>> getmMovies(){ return mMovies; }

    public LiveData<List<Genres>> getmGenres(){ return mGenres; }

    public LiveData<Integer> getmLastPosition(){ return mLastPosition; }

    public void addMovieToFavourites(Movies movie){
        moviesRepositoryDb.getInstance(getApplication());
        moviesRepositoryDb.addMovieToFavourites(movie);

    }

    public boolean checkIfMovieIsInFavourites(Movies movie){
        moviesRepositoryDb.getInstance(getApplication());
        return moviesRepositoryDb.checkIfMovieIsInFavourites(movie);
    }

    public void setLastAdapterPosition(Integer position){
        mLastPosition.postValue(position);
    }
}
