package io.mycompany.androidpractice.adapter;

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
import io.mycompany.androidpractice.util.DialogFavItemsDesc;

public class FavListItemAdapter extends RecyclerView.Adapter<FavListItemAdapter.FavItemViewHolder>  {

    @Nullable
    private CallFragmentFromAdapter callback;
    @Nullable
    private CreateDialogFragment callbackDialogFragment;
    @Nullable
    private DialogItemDescriptionListener mListener;

    private List<Card> cardList;

    public FavListItemAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public FavItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_list_item, parent, false);

        return new FavItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavItemViewHolder holder, final int position) {
        final Card card = cardList.get(position);

        holder.imageView.setImageDrawable(holder.imageView.getResources().getDrawable(card.getImage()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if (!isLongClick){
                    if (mListener != null) {
                        mListener.onDialogCollingDesc(card.getHeading(), card.getDescription());
                    }
                } else {
                    if (callbackDialogFragment != null) {
                        callbackDialogFragment.createDialogFragment(cardList.get(position).getId());
                    }
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

    public void positiveClick(int id) {
        if (deleteAndNotifyList(id)) {
            if (callback != null) {
                callback.notifyToChangeFragmentTwo();
            }
        }
    }

    public class FavItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imageView;
        private ItemClickListener itemClickListener;

        FavItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            imageView = itemView.findViewById(R.id.imageViewFav);

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

    //interface to call from activity ze fragment dialog
    public interface CreateDialogFragment {
        void createDialogFragment(int id);
    }

    public void setCallback(@Nullable CreateDialogFragment callback) {
        this.callbackDialogFragment = callback;
    }

    //interface to call from activity ze fragment dialog desc
    public interface DialogItemDescriptionListener {
        void onDialogCollingDesc(String heading, String desc);
    }

    public void setmListener(@Nullable DialogItemDescriptionListener mListener) {
        this.mListener = mListener;
    }


}
