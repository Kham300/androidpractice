package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mycompany.androidpractice.R;

public class ExampleFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_example;
    private View view;

    public static ExampleFragment getInstance(){
        Bundle bundle = new Bundle();

        ExampleFragment exampleFragment = new ExampleFragment();
        exampleFragment.setArguments(bundle);

        return exampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }

}
