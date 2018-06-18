package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.adapter.CardAdapter;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.DataUtil;
import io.mycompany.androidpractice.util.DataUtilSimple;

public class FragmentTwo extends Fragment {

    private static final int LAYOUT = R.layout.fragment_two;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(LAYOUT, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CardAdapter cardAdapter = new CardAdapter(DataUtilSimple.allListData);
        recyclerView.setAdapter(cardAdapter);
        //TODO обновление адаптера или или
//        cardAdapter.notifyDataSetChanged();

        return view;
    }


}
