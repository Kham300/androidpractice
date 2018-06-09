package io.mycompany.androidpractice;

import android.support.v4.app.FragmentTransaction;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import io.mycompany.androidpractice.fragments.FragmentOne;
import io.mycompany.androidpractice.fragments.FragmentThree;
import io.mycompany.androidpractice.fragments.FragmentTwo;
import io.mycompany.androidpractice.fragments.onMoveFragmentListener;


public class PageViewActivity extends AppCompatActivity implements onMoveFragmentListener {

    private final static int FRAGMENT = R.id.fragment;
    private FragmentThree frag3;
    private FragmentTwo frag2;
    private FragmentOne frag1;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        initToolbar();

        frag1 = new FragmentOne();
        frag1.setMoveFragmentListener(this);

        frag2 = new FragmentTwo();
        frag2.setMoveFragmentListener(this);

        frag3 = new FragmentThree();
        frag3.setMoveFragmentListener(this);

        ft.add(R.id.fragment, frag1);
        ft.commit();

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void changeFragment(int i) {
        switch (i) {
            case 1:
                replaceFragment(frag1);
                toolbar.setTitle("One");
                break;
            case 2:
                replaceFragment(frag2);
                toolbar.setTitle("Two");
                break;
            case 3:
                replaceFragment(frag3);
                toolbar.setTitle("Three");
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(FRAGMENT, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
