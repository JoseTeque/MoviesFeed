package m.google.moviesfeed.movies;

import io.reactivex.Observable;
import m.google.moviesfeed.http.apimodel.ResultsItem;

public interface MoviesInterfacesRepository {

    Observable<ResultsItem> getResultFromNetwork();
    Observable<ResultsItem> getResultFromCache();
    Observable<ResultsItem> getResultData();

    Observable<String> getCountryFromNetwork();
    Observable<String> getCountryFromCache();
    Observable<String> getCountryData();
}
