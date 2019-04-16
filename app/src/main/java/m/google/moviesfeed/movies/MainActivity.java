package m.google.moviesfeed.movies;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.google.moviesfeed.R;
import m.google.moviesfeed.adapter.ListAdapter;
import m.google.moviesfeed.modelo.ViewModel;
import m.google.moviesfeed.root.App;

public class MainActivity extends AppCompatActivity implements MoviesMVP.View {

   private final String TAG= MainActivity.class.getName();

   @BindView(R.id.IdRecycler)
    RecyclerView recyclerView;

   @BindView(R.id.IdMainView)
    ConstraintLayout constraintLayout;

   @Inject
   MoviesMVP.Presenter presenter;

   private ListAdapter adapter;
   private List<ViewModel> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App)getApplication()).getComponent().inject(this);

        adapter= new ListAdapter(this, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateDate(ViewModel viewModel) {
      list.add(viewModel);
      adapter.notifyItemInserted(list.size()-1);
        Log.d(TAG,"Informacion nueva :" + viewModel.getName());


    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(constraintLayout,message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();

    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.rxjavaUnsuscribe();
        adapter.notifyDataSetChanged();
        list.clear();
    }
}
