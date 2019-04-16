package m.google.moviesfeed.root;

import javax.inject.Singleton;

import dagger.Component;
import m.google.moviesfeed.http.modulehttp.MovieModule;
import m.google.moviesfeed.http.modulehttp.OmbdModule;
import m.google.moviesfeed.movies.MainActivity;
import m.google.moviesfeed.movies.MoviesModule;

@Singleton
@Component(modules = {ApplicationModule.class, MoviesModule.class, MovieModule.class, OmbdModule.class})
public interface ApplcationComponent {

   void inject(MainActivity target);
}
