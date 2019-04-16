package m.google.moviesfeed.movies;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import m.google.moviesfeed.http.apimodel.ResultsItem;
import m.google.moviesfeed.http.apimodel.TmdbApi;
import m.google.moviesfeed.http.interfaces.OmdbInterfaces;
import m.google.moviesfeed.http.interfaces.TmdbInterfaces;
import m.google.moviesfeed.http.omdbApi.OmdbModel;

public class MoviesRepository implements MoviesInterfacesRepository {

    private TmdbInterfaces interfaces;
    private OmdbInterfaces omdbInterfaces;

    private List<String> countries;
    private List<ResultsItem> results;
    private long lastTimes;

    private static final long CACHE_LIFETIME = 20 * 1000; //cache de durara 20seg


    public MoviesRepository(TmdbInterfaces interfaces, OmdbInterfaces omdbInterfaces) {
        this.interfaces = interfaces;
        this.omdbInterfaces = omdbInterfaces;

        this.lastTimes = System.currentTimeMillis();

        this.results = new ArrayList<>();
        this.countries = new ArrayList<>();
    }

    public boolean isUpdated() {
        return (System.currentTimeMillis() - lastTimes) < CACHE_LIFETIME;
    }

    @Override
    public Observable<ResultsItem> getResultFromNetwork() {
        Observable<TmdbApi> topmovie = interfaces.getResult(1)
                .concatWith(interfaces.getResult(2)).concatWith(interfaces.getResult(3));

        return topmovie.concatMap(new Function<TmdbApi, Observable<ResultsItem>>() {
            @Override
            public Observable<ResultsItem> apply(TmdbApi tmdbApi) {
                return Observable.fromIterable(tmdbApi.getResults());
            }
        }).doOnNext(new Consumer<ResultsItem>() {
            @Override
            public void accept(ResultsItem resultsItem) {
                results.add(resultsItem);
            }
        });
    }

    @Override
    public Observable<ResultsItem> getResultFromCache() {
        if (isUpdated()) {
            return Observable.fromIterable(results);
        } else {
            lastTimes = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<ResultsItem> getResultData() {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork());
    }

    @Override
    public Observable<String> getCountryFromNetwork() {
        return getResultFromNetwork().concatMap(new Function<ResultsItem, Observable<OmdbModel>>() {
            @Override
            public Observable<OmdbModel> apply(ResultsItem resultsItem) {
                return omdbInterfaces.getTitle(resultsItem.getTitle());
            }
        }).concatMap(new Function<OmdbModel, Observable<String>>() {
            @Override
            public Observable<String> apply(OmdbModel omdbModel) throws Exception {
                if (omdbModel == null || omdbModel.getCountry() == null) {
                    return Observable.just("Desconocido..");
                } else
                    return Observable.just(omdbModel.getCountry());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String country) {
                countries.add(country);
            }
        });
    }

    @Override
    public Observable<String> getCountryFromCache() {
        if (isUpdated()) {
            return Observable.fromIterable(countries);
        } else {
            lastTimes = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountryFromCache().switchIfEmpty(getCountryFromNetwork());
    }
}
