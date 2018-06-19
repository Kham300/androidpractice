package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.adapter.FavListItemAdapter;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.AlertDialogHelper;
import io.mycompany.androidpractice.util.DataUtilSimple;
import io.mycompany.androidpractice.util.RecyclerItemClickListener;

public class FragmentOne extends Fragment {

    //TODO сделать иконки и при нажатии выводить тоаст а при долгом нажатии чтобы выскакивало меню
    private static final int LAYOUT = R.layout.fragment_one_layout;
    private FavListItemAdapter favListItemAdapter;
    private boolean isMultiSelect = false;
    ArrayList<Card> user_list = new ArrayList<>();
    ArrayList<Card> multiselect_list = new ArrayList<>();
    ActionMode mActionMode;
    AlertDialogHelper alertDialogHelper;
    Menu context_menu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(LAYOUT, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_one);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        favListItemAdapter = new FavListItemAdapter(DataUtilSimple.favoriteList);
        recyclerView.setAdapter(favListItemAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect){
                    multiSelect(position);
                } else {
                    Toast.makeText(getContext(),"Details Page", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (!isMultiSelect){
                    multiselect_list = new ArrayList<>();
                    isMultiSelect = true;

                    if (mActionMode == null){
                        mActionMode = getActivity().startActionMode(mActionModeCallback);
                    }
                }

                multiSelect(position);

            }
        }));
        return view;
    }



    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            context_menu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    alertDialogHelper.showAlertDialog("","Delete Contact","DELETE","CANCEL",1,false);
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            isMultiSelect = false;
            multiselect_list = new ArrayList<Card>();
            refreshAdapter();

        }

    };


    public void multiSelect(int position) {
        if (mActionMode != null) {
            if (multiselect_list.contains(user_list.get(position)))
                multiselect_list.remove(user_list.get(position));
            else
                multiselect_list.add(user_list.get(position));

            if (multiselect_list.size() > 0)
                mActionMode.setTitle("" + multiselect_list.size());
            else
                mActionMode.setTitle("");

            refreshAdapter();

        }
    }

    public void refreshAdapter()
    {
        favListItemAdapter.selected_usersList=multiselect_list;
        favListItemAdapter.usersList=user_list;
        favListItemAdapter.notifyDataSetChanged();
    }

    public void refreshUi() {
        favListItemAdapter.notifyDataSetChanged();
    }

}