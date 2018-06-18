package io.mycompany.androidpractice;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import io.mycompany.androidpractice.adapter.TabsPagerFragmentAdapter;
import io.mycompany.androidpractice.fragments.FragmentOne;
import io.mycompany.androidpractice.fragments.FragmentThree;
import io.mycompany.androidpractice.fragments.FragmentTwo;
import io.mycompany.androidpractice.util.DataUtilSimple;

public class PageViewActivity extends AppCompatActivity {


    private ViewPager viewPager;


    TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);

        initToolbar();
        initTabs();

        clearFragment();

    }

    private void clearFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null && fragment.isVisible() && fragment instanceof FragmentOne) {
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(fragment);
                ft.attach(fragment);
                ft.commit();
            }
        }
    }


    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataUtilSimple.removeAllItems();
                onBackPressed();
            }
        });
    }

    private void initTabs() {
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);


        //set fragments
        adapter.addFragments(new FragmentOne(), "Chosen");
        adapter.addFragments(new FragmentTwo(), "List");
        adapter.addFragments(new FragmentThree(), "Three");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    for (Fragment fragment : fragmentManager.getFragments()) {
                        if (fragment != null && fragment.isVisible() && fragment instanceof FragmentOne) {
                            ((FragmentOne) fragment).refreshUi();
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
