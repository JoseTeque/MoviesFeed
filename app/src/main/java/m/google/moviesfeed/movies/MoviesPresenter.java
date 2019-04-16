package m.google.moviesfeed.movies;

import android.support.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import m.google.moviesfeed.modelo.ViewModel;

public class MoviesPresenter implements MoviesMVP.Presenter {

    @Nullable
    private MoviesMVP.View view;
    private MoviesMVP.Model model;

    private Disposable subscription;

    public MoviesPresenter(MoviesMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
      subscription = model.results()
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(new DisposableObserver<ViewModel>() {
                  @Override
                  public void onNext(ViewModel viewModel) {
                      if (view!=null){
                          view.updateDate(viewModel);
                      }
                  }

                  @Override
                  public void onError(Throwable e) {
                    e.printStackTrace();
                    if (view!=null){
                        view.showSnackbar("Error al descargar las peliculas..");
                    }
                  }

                  @Override
                  public void onComplete() {
                    if (view!=null){
                        view.showSnackbar("Informacion descargada con exito..");
                    }
                  }
              });
    }

    @Override
    public void rxjavaUnsuscribe() {
        if (subscription!=null && !subscription.isDisposed())
        {
            subscription.dispose();
        }

    }

    @Override
    public void setView(MoviesMVP.View view) {
       this.view= view;
    }
}
