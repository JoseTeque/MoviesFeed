package m.google.moviesfeed.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.google.moviesfeed.R;
import m.google.moviesfeed.modelo.ViewModel;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

  private Context context;
  private List<ViewModel> listMovies;

    public ListAdapter(Context context, List<ViewModel> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewModel model= listMovies.get(i);

       viewHolder.txtName.setText(model.getName());
       viewHolder.txtPais.setText(model.getPais());
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.IdTxtNameMovie)
        public   TextView txtName;

        @BindView(R.id.IdTxtPaisMovie)
         public     TextView txtPais;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
