package m.google.moviesfeed.movies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import m.google.moviesfeed.http.interfaces.OmdbInterfaces;
import m.google.moviesfeed.http.interfaces.TmdbInterfaces;

@Module
public class MoviesModule {

    @Provides
    public MoviesMVP.Presenter providesPresenter(MoviesMVP.Model model){
        return new MoviesPresenter(model);
    }

    @Provides
    public MoviesMVP.Model providesModel(MoviesInterfacesRepository repository){
        return new MoviesModel(repository);
    }

    @Singleton
    @Provides
    public  MoviesInterfacesRepository providesRepository(TmdbInterfaces tmdbInterfaces, OmdbInterfaces omdbInterfaces){
       return new MoviesRepository(tmdbInterfaces, omdbInterfaces);
    }

}
