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

    public static final String DIALOG_TAG = "about";

    public static final String HEAD = "head";

    public static final String DESC = "desc";

    public DialogFavItemsDesc() {
    }

    public static DialogFavItemsDesc newInstance(){
        //todo пустой метод. newInstance нужен тогда, когда ты создаешь экземпляр объекта с какими-то параметрами.
        return new DialogFavItemsDesc();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fav_item_desc, container);
        TextView heading = view.findViewById(R.id.heading);
        TextView desc = view.findViewById(R.id.description);
        if (getArguments() != null) {
            //todo не нашел, где сетятся arguments
            getDialog().setTitle("about");
            heading.setText(getArguments().getString(HEAD));
            desc.setText(getArguments().getString(DESC));
        }
        return view;
    }

}
