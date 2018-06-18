package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.adapter.FavListItemAdapter;
import io.mycompany.androidpractice.util.DataUtilSimple;

public class FragmentOne extends Fragment {

    //TODO сделать иконки и при нажатии выводить тоаст а при долгом нажатии чтобы выскакивало меню
    private static final int LAYOUT = R.layout.fragment_one_layout;
    private FavListItemAdapter favListItemAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(LAYOUT, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_one);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        favListItemAdapter = new FavListItemAdapter(DataUtilSimple.favoriteList);
        recyclerView.setAdapter(favListItemAdapter);
        return view;
    }

    public void refreshUi() {
        favListItemAdapter.notifyDataSetChanged();
    }

}