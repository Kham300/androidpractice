package io.mycompany.androidpractice;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import io.mycompany.androidpractice.adapter.TabsPagerFragmentAdapter;
import io.mycompany.androidpractice.fragments.FragmentOne;
import io.mycompany.androidpractice.fragments.FragmentThree;
import io.mycompany.androidpractice.fragments.FragmentTwo;
import io.mycompany.androidpractice.util.DataUtilSimple;

public class PageViewActivity extends AppCompatActivity implements FragmentOne.CallActivityFromFragment {

    private ViewPager viewPager;

    TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        initToolbar();
        initTabs();
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

        if (getSupportActionBar() != null) {
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

        FragmentOne fragmentOne = new FragmentOne();
        fragmentOne.setCallBackToActivity(this);

        //set fragments
        adapter.addFragments(fragmentOne, FragmentOne.FRAGMENT_ONE_TAG);
        adapter.addFragments(new FragmentTwo(), FragmentTwo.FRAGMENT_TWO_TAG);
        adapter.addFragments(new FragmentThree(), FragmentThree.FRAGMENT_THREE_TAG);

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    FragmentOne fragment = (FragmentOne) getSupportFragmentManager()
                            .findFragmentByTag(FragmentOne.FRAGMENT_ONE_TAG);
                    fragment.refreshUi();
                }

                invalidateOptionsMenu(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        invalidateOptionsMenu(0);
    }

    private void invalidateOptionsMenu(int position) {
        for (int i = 0; i < adapter.getCount(); i++) {
            Fragment fragment = adapter.getItem(i);
            fragment.setHasOptionsMenu(i == position);
        }
        invalidateOptionsMenu();
    }

    @Override
    public void refreshDataInFragmentTwo() {
        FragmentTwo fragment = (FragmentTwo) getSupportFragmentManager()
                .findFragmentByTag(FragmentTwo.FRAGMENT_TWO_TAG);
        fragment.refreshUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_refresh:
                refreshAllData();
                return true;
            case R.id.action_actions:
                showToast("Activity");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshAllData() {
        showToast("Refresh data from activity");
        adapter.notifyDataSetChanged();
    }

    public void moveToRight(View view) {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem != 2) {
            viewPager.setCurrentItem(currentItem + 1, true);
        } else {
            viewPager.setCurrentItem(0, true);
        }
    }

    public void moveToLeft(View view) {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem != 0) {
            viewPager.setCurrentItem(currentItem - 1, true);
        } else {
            viewPager.setCurrentItem(3, true);
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


}
