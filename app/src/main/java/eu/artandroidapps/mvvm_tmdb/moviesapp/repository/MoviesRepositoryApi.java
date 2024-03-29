package eu.artandroidapps.mvvm_tmdb.moviesapp.repository;

import android.app.Application;
import android.util.Log;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetGenresCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetMovieCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetMoviesCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetReviewsCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetTotalPagesCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetTotalResultsCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.OnGetTrailersCallback;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.TheMovieDbService;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.GenresResponse;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Movies;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.MoviesResponse;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.ReviewsResponse;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.TrailersResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepositoryApi {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static String LANGUAGE = "en-US";
    private static final String API_KEY = "API_KEY";
    private static final String MEDIA_TYPE = "movie";
    private static final String TIME_WINDOW = "day";
    public static MoviesRepositoryApi repository;
    private TheMovieDbService theMovieDbService;

    public MoviesRepositoryApi(Application application, TheMovieDbService api,String language){
        theMovieDbService = api;
        LANGUAGE = language;
    }

    public static MoviesRepositoryApi getInstance(Application application){
        if(repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            repository = new MoviesRepositoryApi(application,retrofit.create(TheMovieDbService.class),LANGUAGE);
        }
        return repository;
    }

    public void getPopularMovies(final OnGetMoviesCallback callback){
        theMovieDbService.getPopular(API_KEY,LANGUAGE,1)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if(response.isSuccessful()){
                            MoviesResponse moviesResponse = response.body();
                            if(moviesResponse != null && moviesResponse.getMovies() != null) {
                                callback.onSuccess(moviesResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }
                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.d(MoviesRepositoryApi.class.toString(),"Failed to get movies");
                        callback.onError();
                    }
                });
    }

    public void getGenres(final OnGetGenresCallback callback){
        theMovieDbService.getGenres(API_KEY,LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if(response.isSuccessful()){
                            GenresResponse genresResponse = response.body();
                            if(genresResponse != null && genresResponse.getGenres() != null) {
                                callback.onSuccess(genresResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            Log.d(MoviesRepositoryApi.class.toString(),"Failed to get genres");
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getPopularMoviesNextPage(final OnGetMoviesCallback callback,int page){
        theMovieDbService.getPopular(API_KEY,LANGUAGE,page)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if(response.isSuccessful()){
                            MoviesResponse moviesResponse = response.body();
                            if(moviesResponse != null && moviesResponse.getMovies() != null) {
                                callback.onSuccess(moviesResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }
                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.d(MoviesRepositoryApi.class.toString(),"Failed to get movies");
                        callback.onError();
                    }
                });
    }

    public void getMovie(int movieId, final OnGetMovieCallback callback) {
        theMovieDbService.getMovie(movieId, API_KEY, LANGUAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        if (response.isSuccessful()) {
                            Movies movie = response.body();
                            if (movie != null) {
                                callback.onSuccess(movie);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }
                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getReviews(int movieId, final OnGetReviewsCallback callback){
        theMovieDbService.getReviews(movieId,API_KEY,LANGUAGE)
                .enqueue(new Callback<ReviewsResponse>() {
                    @Override
                    public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                        if(response.isSuccessful()){
                            ReviewsResponse reviewsResponse = response.body();
                            if(reviewsResponse != null && reviewsResponse.getReviews() != null){
                                callback.onSuccess(reviewsResponse.getReviews());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }
                    @Override
                    public void onFailure(Call<ReviewsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getTrailers(int movieId, final OnGetTrailersCallback callback) {
        theMovieDbService.getTrailers(movieId,API_KEY, LANGUAGE)
                .enqueue(new Callback<TrailersResponse>() {
                    @Override
                    public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {
                        if (response.isSuccessful()) {
                            TrailersResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {
                                callback.onSuccess(trailerResponse.getTrailers());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }
                    @Override
                    public void onFailure(Call<TrailersResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getSearchMovies(String query,final OnGetMoviesCallback callback){
        theMovieDbService.getSearchMovies(API_KEY,LANGUAGE,1,query).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                    MoviesResponse moviesResponse = response.body();
                    if(moviesResponse != null && moviesResponse.getMovies() != null) {
                        callback.onSuccess(moviesResponse.getMovies());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to get search movies");
                callback.onError();
            }
        });
    }

    public void getSearchMoviesTotalResults(String query,final OnGetTotalResultsCallback callback){
        theMovieDbService.getSearchMovies(API_KEY,LANGUAGE,1,query).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                    MoviesResponse moviesResponse = response.body();
                    if(moviesResponse != null && moviesResponse.getMovies() != null) {
                        callback.onSuccess(moviesResponse.getTotalResults());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to get search movies");
                callback.onError();
            }
        });
    }

    public void getSearchMoviesTotalPages(String query,final OnGetTotalPagesCallback callback){
        theMovieDbService.getSearchMovies(API_KEY,LANGUAGE,1,query).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                    MoviesResponse moviesResponse = response.body();
                    if(moviesResponse != null && moviesResponse.getMovies() != null) {
                        callback.onSuccess(moviesResponse.getTotalPages());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to get search movies");
                callback.onError();
            }
        });
    }

    public void getSearchMoviesNextPage(String query,Integer page,final OnGetMoviesCallback callback){
        theMovieDbService.getSearchMovies(API_KEY,LANGUAGE,page,query).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                    MoviesResponse moviesResponse = response.body();
                    if(moviesResponse != null && moviesResponse.getMovies() != null) {
                        callback.onSuccess(moviesResponse.getMovies());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(MoviesRepositoryApi.class.toString(),"Failed to get search movies");
                callback.onError();
            }
        });
    }

    public void getTrendingMovies(final OnGetMoviesCallback callback){
        theMovieDbService.getTrendingMovies(API_KEY,MEDIA_TYPE,TIME_WINDOW)
                .enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    if(response.isSuccessful()){
                        MoviesResponse moviesResponse = response.body();
                        if(moviesResponse != null && moviesResponse.getMovies() != null) {
                            callback.onSuccess(moviesResponse.getMovies());
                        } else {
                            callback.onError();
                        }
                    } else {
                        callback.onError();
                    }
                }
                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Log.d(MoviesRepositoryApi.class.toString(),"Failed to get trending movies");
                    callback.onError();
                }
        });
    }
}
