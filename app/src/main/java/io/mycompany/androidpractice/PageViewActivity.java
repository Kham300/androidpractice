package io.mycompany.androidpractice;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Objects;

import io.mycompany.androidpractice.adapter.TabsPagerFragmentAdapter;
import io.mycompany.androidpractice.fragments.FragmentOne;
import io.mycompany.androidpractice.fragments.FragmentThree;
import io.mycompany.androidpractice.fragments.FragmentTwo;

public class PageViewActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);

        initToolbar();
        initTabs();

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initTabs() {

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);


        //set fragments
        adapter.addFragments(new FragmentOne(), "One");
        adapter.addFragments(new FragmentTwo(), "Two");
        adapter.addFragments(new FragmentThree(), "Three");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void moveToRight(View view){
        int currentItem = viewPager.getCurrentItem();
        if (currentItem != 2) {
            viewPager.setCurrentItem(currentItem + 1, true);
        } else {
            viewPager.setCurrentItem(0, true);
        }
    }


    public void moveToLeft(View view){
        int currentItem = viewPager.getCurrentItem();
        if (currentItem != 0) {
            viewPager.setCurrentItem(currentItem - 1, true);
        } else {
            viewPager.setCurrentItem(3, true);
        }
    }
}
