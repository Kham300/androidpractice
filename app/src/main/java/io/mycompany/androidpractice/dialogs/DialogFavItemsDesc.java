package io.mycompany.androidpractice.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.mycompany.androidpractice.R;

public class DialogFavItemsDesc extends DialogFragment {

    public DialogFavItemsDesc() {
    }

    public static DialogFavItemsDesc newInstance(){
        return new DialogFavItemsDesc();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fav_item_desc, container);
        TextView heading = view.findViewById(R.id.heading);
        TextView desc = view.findViewById(R.id.description);
        if (getArguments() != null) {
            getDialog().setTitle("about");
            heading.setText(getArguments().getString("head"));
            desc.setText(getArguments().getString("desc"));
        }
        return view;
    }

}
