package m.google.moviesfeed.movies;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import m.google.moviesfeed.http.apimodel.ResultsItem;
import m.google.moviesfeed.http.apimodel.TmdbApi;
import m.google.moviesfeed.modelo.ViewModel;

public class MoviesModel implements MoviesMVP.Model {

    private MoviesInterfacesRepository repository;

    public MoviesModel(MoviesInterfacesRepository repository){
        this.repository= repository;
    }

    @Override
    public Observable<ViewModel> results() {
        return Observable.zip(repository.getResultData(), repository.getCountryData(), new BiFunction<ResultsItem, String, ViewModel>() {
            @Override
            public ViewModel apply(ResultsItem resultData, String country)  {
                return new ViewModel(resultData.getTitle(),country);
            }
        });
    }
}
