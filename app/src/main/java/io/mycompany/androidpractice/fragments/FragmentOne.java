package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.adapter.CardAdapter;
import io.mycompany.androidpractice.util.DataUtil;

public class FragmentOne extends Fragment {


    //TODO сделать иконки и при нажатии выводить тоаст а при долгом нажатии чтобы выскакивало меню
    private static final int LAYOUT = R.layout.fragment_one;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(LAYOUT, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_one);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        cardAdapter = new CardAdapter(DataUtil.getInstance().getChosenList());
        recyclerView.setAdapter(cardAdapter);
        return view;
    }

    public void refreshUi() {
        cardAdapter.notifyDataSetChanged();
    }
}
