package io.mycompany.androidpractice.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mycompany.androidpractice.R;

public class DialogToFavItems extends DialogFragment {

    @Nullable
    private DialogToFavItemsListener mListener;

    public DialogToFavItems() {
    }

    public static DialogToFavItems newInstance(){
        String title = "Warning!";
        DialogToFavItems dialogToFavItems = new DialogToFavItems();
        Bundle args = new Bundle();
        args.putString("title", title);
        dialogToFavItems.setArguments(args);

        return dialogToFavItems;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String title = null;
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        getDialog().setTitle(title);
        View view = inflater.inflate(R.layout.dialog_fav_item, container);
        view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDialogPositiveClick(DialogToFavItems.this);
                    dismiss();
                }
            }
        });
        view.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.textView1);
        return view;
    }

    public interface DialogToFavItemsListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }

    public void setmListener(@Nullable DialogToFavItemsListener mListener) {
        this.mListener = mListener;
    }
}
