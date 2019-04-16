package m.google.moviesfeed.http.interfaces;

import io.reactivex.Observable;
import m.google.moviesfeed.http.omdbApi.OmdbModel;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbInterfaces {

    @GET("/")
    Observable<OmdbModel> getTitle(@Query("t") String title);
}
