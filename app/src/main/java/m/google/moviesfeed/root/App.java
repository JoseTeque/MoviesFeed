package m.google.moviesfeed.root;

import android.app.Application;

import m.google.moviesfeed.http.modulehttp.MovieModule;
import m.google.moviesfeed.http.modulehttp.OmbdModule;
import m.google.moviesfeed.movies.MoviesModule;

public class App extends Application {

   private ApplcationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
      component= DaggerApplcationComponent.builder()
              .applicationModule(new ApplicationModule(this))
              .movieModule(new MovieModule())
              .moviesModule(new MoviesModule())
              .ombdModule(new OmbdModule())
              .build();

    }

    public ApplcationComponent getComponent(){
       return component;
    }
}
