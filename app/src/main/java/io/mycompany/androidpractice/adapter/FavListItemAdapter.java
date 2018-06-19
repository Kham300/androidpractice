package io.mycompany.androidpractice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;

public class FavListItemAdapter extends RecyclerView.Adapter<FavListItemAdapter.FavItemViewHolder> {

    private List<Card> cardList;
    private boolean multiSelect = false;
    private ArrayList<Integer> selectedItems = new ArrayList<Integer>();
    public ArrayList<Card> selected_usersList;
    public ArrayList<Card> usersList;

    public FavListItemAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public FavItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_list_item, parent, false);
        return new FavItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavItemViewHolder holder, int position) {
        final Card card = cardList.get(position);

        holder.imageView.setImageDrawable(holder.imageView.getResources().getDrawable(card.getImage()));
        holder.textViewDescriptionFav.setText(card.getDescription());
        holder.textViewHeadingFav.setText(card.getHeading());
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }


    public class FavItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewHeadingFav, textViewDescriptionFav;


        public FavItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewFav);
            textViewHeadingFav = itemView.findViewById(R.id.textViewHeadFav);
            textViewDescriptionFav = itemView.findViewById(R.id.textViewDescriptionFav);
        }
    }
}
