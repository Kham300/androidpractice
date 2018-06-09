package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.mycompany.androidpractice.R;

public class FragmentTwo extends Fragment {

    private static final int LAYOUT = R.layout.fragment_two;

    private onMoveFragmentListener moveFragmentListener;

    public void setMoveFragmentListener(onMoveFragmentListener moveFragmentListener) {
        this.moveFragmentListener = moveFragmentListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(LAYOUT, container, false);

        Button buttonLeft = view.findViewById(R.id.button2_left);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveFragmentListener.changeFragment(1);
            }
        });

        Button buttonRight = view.findViewById(R.id.button2_right);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveFragmentListener.changeFragment(3);
            }
        });

        return view;
    }
}
