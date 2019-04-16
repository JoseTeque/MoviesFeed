package m.google.moviesfeed.http.interfaces;

import io.reactivex.Observable;
import m.google.moviesfeed.http.apimodel.TmdbApi;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TmdbInterfaces {

    @GET("movie/top_rated")
    Observable<TmdbApi> getResult(@Query("page") Integer page);
}
