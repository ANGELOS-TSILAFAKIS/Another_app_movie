package eu.artandroidapps.mvvm_tmdb.moviesapp.api;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.GenresResponse;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Movies;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.MoviesResponse;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.MoviesTrendingResponse;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.ReviewsResponse;
import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbService {
    @GET("movie/popular")
    Call<MoviesResponse> getPopular(@Query("api_key") String apiKey,
                                    @Query("language") String language,
                                    @Query("page") int page);
    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("search/movie")
    Call<MoviesResponse> getSearchMovies(@Query("api_key") String apiKey,
                                                       @Query("language") String language,
                                                       @Query("page") int page,
                                                       @Query("query") String searchQuery);
    @GET("movie/{movie_id}")
    Call<Movies> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("movie/{movie_id}/videos")
    Call<TrailersResponse> getTrailers(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/reviews")
    Call<ReviewsResponse> getReviews(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("trending/{media_type}/{time_window}")
    Call<MoviesResponse> getTrendingMovies(
            @Path("media_type") String media_type,
            @Path("time_window") String time_window,
            @Query("api_key") String apiKey);
}
