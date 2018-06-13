package io.mycompany.androidpractice.adapter;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.fragments.FragmentOne;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.DataUtil;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList;

    public CardAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        final Card card = cardList.get(position);

        holder.imageView.setImageDrawable(holder.imageView.getResources().getDrawable(card.getImage()));
        holder.textViewHeading.setText(card.getHeading());
        holder.textViewDescription.setText(card.getDescription());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    card.setEnabled(true);

//                    refreshUi();
                } else {
                    card.setEnabled(false);
                }
            }
        });

        holder.checkBox.setChecked(card.isEnabled());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewHeading, textViewDescription;
        private CheckBox checkBox;

        public CardViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewHeading = itemView.findViewById(R.id.textViewHead);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }


}
