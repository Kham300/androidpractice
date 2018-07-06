package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mycompany.androidpractice.R;

public class FragmentThree extends Fragment {

    public static final String FRAGMENT_THREE_TAG = "Fragment three";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        return inflater.inflate(R.layout.fragment_three, container, false);
    }
}
