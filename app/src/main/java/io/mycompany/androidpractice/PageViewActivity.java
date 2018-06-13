package io.mycompany.androidpractice;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.mycompany.androidpractice.adapter.CardAdapter;
import io.mycompany.androidpractice.adapter.TabsPagerFragmentAdapter;
import io.mycompany.androidpractice.fragments.FragmentOne;
import io.mycompany.androidpractice.fragments.FragmentThree;
import io.mycompany.androidpractice.fragments.FragmentTwo;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.DataUtil;

public class PageViewActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;


    TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);

        initToolbar();
        initTabs();

    }

    private void initToolbar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
        adapter.addFragments(new FragmentOne(), "Chosen");
        adapter.addFragments(new FragmentTwo(), "List");
        adapter.addFragments(new FragmentThree(), "Three");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
       // viewPager.addOnPageChangeListener(); при прокрутке влево или в право обновить фрагмент справа https://medium.com/@elifbon/fragments-on-viewpager-8ace8430a8e1
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
