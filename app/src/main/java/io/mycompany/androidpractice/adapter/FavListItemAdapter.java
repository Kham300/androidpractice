package io.mycompany.androidpractice.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.DataUtilSimple;

public class FavListItemAdapter extends RecyclerView.Adapter<FavListItemAdapter.FavItemViewHolder> {

    private List<Card> cardList;
    private Context context;
    @Nullable
    private CallFragmentFromAdapter callback;


    public FavListItemAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public FavItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_list_item, parent, false);
        context = view.getContext();

        return new FavItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavItemViewHolder holder, final int position) {
        final Card card = cardList.get(position);

        holder.imageView.setImageDrawable(holder.imageView.getResources().getDrawable(card.getImage()));
        holder.textViewDescriptionFav.setText(card.getDescription());
        holder.textViewHeadingFav.setText(card.getHeading());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if (!isLongClick){
                    Toast.makeText(view.getContext(),"short click: " + position, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Delete")
                            .setCancelable(false)
                            .setMessage("Удалить данный элемент?")
                            .setPositiveButton("Да",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(deleteAndNotifyList(cardList.get(position).getId())){
                                                if (callback != null) {
                                                    callback.notifyToChangeFragmentTwo();
                                                }
                                            }
                                            dialog.cancel();
                                        }
                                    })
                            .setNegativeButton("Нет",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                    builder.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    private boolean deleteAndNotifyList(final int id){
        boolean isDeleted = isDeleted(id);
        if (isDeleted){
            for (Card c : DataUtilSimple.allListData){
                if (id == c.getId()){
                    c.setEnabled(false);
                }
            }
        }
        return isDeleted;
    }

    private boolean isDeleted(int id){
        boolean isDeleted = false;
        for (int i = 0; i < cardList.size(); i++){
            if (id == cardList.get(i).getId()){
                cardList.remove(i);
                notifyDataSetChanged();
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    public class FavItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imageView;
        private TextView textViewHeadingFav, textViewDescriptionFav;
        private ItemClickListener itemClickListener;

        FavItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            imageView = itemView.findViewById(R.id.imageViewFav);
            textViewHeadingFav = itemView.findViewById(R.id.textViewHeadFav);
            textViewDescriptionFav = itemView.findViewById(R.id.textViewDescriptionFav);

        }

        void setItemClickListener(ItemClickListener clickListener){
            this.itemClickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }

    //interface to callback fragment to change data in fragment2
    public interface CallFragmentFromAdapter {
        void notifyToChangeFragmentTwo();
    }

    public void setCallbackToAdapter(@Nullable CallFragmentFromAdapter callback){
        this.callback = callback;
    }

}
