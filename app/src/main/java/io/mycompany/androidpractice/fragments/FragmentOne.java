package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.adapter.FavListItemAdapter;
import io.mycompany.androidpractice.util.DataUtilSimple;
import io.mycompany.androidpractice.dialogs.DialogFavItemsDesc;
import io.mycompany.androidpractice.dialogs.DialogToFavItems;

public class FragmentOne extends Fragment implements FavListItemAdapter.CallFragmentFromAdapter, FavListItemAdapter.CreateDialogFragment,  DialogToFavItems.DialogToFavItemsListener, FavListItemAdapter.DialogItemDescriptionListener {

    @Nullable
    private CallActivityFromFragment callBackToActivity;
    private static final int LAYOUT = R.layout.fragment_one_layout;
    private FavListItemAdapter favListItemAdapter;
    private int itemId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(LAYOUT, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_one);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        favListItemAdapter = new FavListItemAdapter(DataUtilSimple.favoriteList);
        favListItemAdapter.setCallbackToAdapter(this);
        favListItemAdapter.setCallback(this);
        favListItemAdapter.setmListener(this);
        recyclerView.setAdapter(favListItemAdapter);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_one_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.fragment_action_toast:
                makeToast();
                return true;
                default:
            return false;
        }
    }

    public void refreshUi() {
        favListItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyToChangeFragmentTwo() {
        if (callBackToActivity != null) {
            callBackToActivity.refreshDataInFragmentTwo();
        }
    }

    public void makeToast() {
        Toast toast = Toast.makeText(getActivity(),
                "FragmentOne action!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void createDialogFragment(int id) {
        DialogToFavItems dialogToFavItems = DialogToFavItems.newInstance();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        dialogToFavItems.setmListener(this);
        dialogToFavItems.show(manager,"Warning");
        setItemId(id);
    }

    @Override
    public void onDialogCollingDesc(@NonNull String heading, @NonNull String description) {
        DialogFavItemsDesc desc = DialogFavItemsDesc.newInstance();
        FragmentManager ma = getActivity().getSupportFragmentManager();
        Bundle b = new Bundle();
        b.putString("head", heading);
        b.putString("desc", description);
        desc.setArguments(b);
        desc.show(ma, "about");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        favListItemAdapter.positiveClick(itemId);
    }

    public interface CallActivityFromFragment {
        void refreshDataInFragmentTwo();
    }

    public void setCallBackToActivity(@Nullable CallActivityFromFragment callBackToActivity) {
        this.callBackToActivity = callBackToActivity;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}