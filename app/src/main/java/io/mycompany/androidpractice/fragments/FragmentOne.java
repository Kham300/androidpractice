package io.mycompany.androidpractice.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import io.mycompany.androidpractice.dialogs.DialogFavItemsDesc;
import io.mycompany.androidpractice.dialogs.DialogToFavItems;
import io.mycompany.androidpractice.util.DataUtilSimple;

public class FragmentOne extends Fragment
        implements FavListItemAdapter.CallFragmentFromAdapter, FavListItemAdapter.CreateDialogFragment,
        DialogToFavItems.DialogToFavItemsListener, FavListItemAdapter.DialogItemDescriptionListener, IFragment {


    public static final String FRAGMENT_ONE_TAG = "Favorite";

    @Nullable
    private CallActivityFromFragment callBackToActivity;

    private static final int LAYOUT = R.layout.fragment_one_layout;

    private FavListItemAdapter favListItemAdapter;

    private int itemId;

    private DialogToFavItems  dialogToFavItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstance) {
        View view = inflater.inflate(LAYOUT, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_one);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        favListItemAdapter = new FavListItemAdapter(DataUtilSimple.favoriteList);
        favListItemAdapter.setCallbackToAdapter(this);
        favListItemAdapter.setCallback(this);
        favListItemAdapter.setmListener(this);
        dialogToFavItems = DialogToFavItems.newInstance();
        dialogToFavItems.setmListener(this);
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
                Toast.makeText(getActivity(), "FragmentOne action!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshUi() {
        favListItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyToChangeFragmentTwo() {
        if (callBackToActivity != null) {
            callBackToActivity.refreshDataInFragmentTwo();
        }
    }

    @Override
    public void createDialogFragment(int id) {
        itemId = id;
        dialogToFavItems.show(getActivity().getSupportFragmentManager(), "Warning");
    }

    @Override
    public void onDialogCollingDesc(@NonNull String heading, @NonNull String description) {
        DialogFavItemsDesc desc = DialogFavItemsDesc.newInstance();
        Bundle b = new Bundle();
        //берем заранее приготовленные константы чтобы не ошибиться в написании
        b.putString(DialogFavItemsDesc.HEAD, heading);
        b.putString(DialogFavItemsDesc.DESC, description);
        desc.setArguments(b);
        desc.show(getActivity().getSupportFragmentManager(), DialogFavItemsDesc.DIALOG_TAG);
    }

    @Override
    public void onDialogPositiveClick() {
        favListItemAdapter.positiveClick(itemId);
    }

    public interface CallActivityFromFragment {

        void refreshDataInFragmentTwo();
    }

    public void setCallBackToActivity(@Nullable CallActivityFromFragment callBackToActivity) {
        this.callBackToActivity = callBackToActivity;
    }
}