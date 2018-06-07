package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mycompany.androidpractice.R;

public class FragmentThree extends Fragment {

    private static final int LAYOUT = R.layout.fragment_three;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}
