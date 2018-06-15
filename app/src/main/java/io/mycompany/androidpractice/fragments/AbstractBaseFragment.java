package io.mycompany.androidpractice.fragments;

import android.support.v4.app.Fragment;

import io.mycompany.androidpractice.util.DataUtil;

public class AbstractBaseFragment extends Fragment{

    public final DataUtil dataUtil;

    public AbstractBaseFragment() {
        dataUtil = DataUtil.getInstance();
    }
}
