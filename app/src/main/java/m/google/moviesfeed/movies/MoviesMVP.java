package m.google.moviesfeed.movies;



import io.reactivex.Observable;
import m.google.moviesfeed.modelo.ViewModel;

public interface MoviesMVP {

    interface  View{

        void updateDate(ViewModel viewModel);

        void showSnackbar(String s);
    }

    interface Model{
      Observable<ViewModel> results();

    }

    interface Presenter{

        void loadData();

        void rxjavaUnsuscribe();

        void setView(MoviesMVP.View view);

    }

}
