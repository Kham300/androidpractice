package io.mycompany.androidpractice.adapter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.DataUtil;
import io.mycompany.androidpractice.util.DataUtilSimple;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList;
    public CardAdapter(List<Card> list) {
        this.cardList = list;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_list_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Card card = cardList.get(position);

        holder.imageView.setImageDrawable(holder.imageView.getResources().getDrawable(card.getImage()));
        holder.textViewHeading.setText(card.getHeading());
        holder.textViewDescription.setText(card.getDescription());
        holder.checkBox.setChecked(card.isEnabled());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, final int position, boolean isLongClick) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("Item " + cardList.get(position).getHeading())
                        .setMessage("Удлить данный элемент?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Card card1 = cardList.get(position);
                                DataUtilSimple.allListData.remove(card1);
                                DataUtilSimple.removeFavItem(card1);
                                notifyItemRemoved(position);
                                Toast.makeText(view.getContext(), "Item deleted",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(view.getContext(), "Cancel",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.show();
            }
        });

        if (card.isEnabled() && !DataUtilSimple.favoriteList.contains(card)){
            DataUtilSimple.favoriteList.add(card);
        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        private ItemClickListener itemClickListener;
        private ImageView imageView;
        private TextView textViewHeading, textViewDescription;
        private CheckBox checkBox;

        CardViewHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            textViewHeading = itemView.findViewById(R.id.textViewHead);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            checkBox = itemView.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        DataUtilSimple.addFavItem(cardList.get(getAdapterPosition()));
                        DataUtilSimple.allListData.get(getAdapterPosition()).setEnabled(true);
                        cardList.get(getAdapterPosition()).setEnabled(true);
                    } else {
                        DataUtilSimple.removeFavItem(cardList.get(getAdapterPosition()));
                        DataUtilSimple.allListData.get(getAdapterPosition()).setEnabled(false);
                        cardList.get(getAdapterPosition()).setEnabled(false);
                    }
                }
            });

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }

        void setItemClickListener(ItemClickListener clickListener){
            this.itemClickListener = clickListener;
        }

    }
}
