package io.mycompany.androidpractice.util;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;

public class DataUtil extends Application {

    private static DataUtil INSTANCE;

    private List<Card> chosenList;
    private List<Card> allDataList;


    public DataUtil(){
        allDataList = new ArrayList<>();
        chosenList = new ArrayList<>();
        initData();
    }

    public static DataUtil getInstance(){
        return INSTANCE;
    }

    @Override
    public final void onCreate(){
        super.onCreate();
        INSTANCE = this;
    }

    private void initData() {
        allDataList.add(
                new Card(
                        1,
                        "Heading 1",
                        "Description 1",
                        R.drawable.ic_android_black_24dp,
                        false));

        allDataList.add(
                new Card(
                        2,
                        "Heading 2",
                        "Description 2",
                        R.drawable.ic_android_black_24dp,
                        true));

        allDataList.add(
                new Card(
                        3,
                        "Heading 3",
                        "Description 3",
                        R.drawable.ic_android_black_24dp,
                        false));

        chosenList.addAll(checkAndAddForChosenList());
    }

//    public List<Card> getAllData(){
//
//
////        allDataList.add(
////                new Card(
////                        4,
////                        "Heading 4",
////                        "Description 4",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        5,
////                        "Heading 5",
////                        "Description 5",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        1,
////                        "Heading 1",
////                        "Description 1",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        2,
////                        "Heading 2",
////                        "Description 2",
////                        R.drawable.ic_android_black_24dp,
////                        true));
////
////        allDataList.add(
////                new Card(
////                        3,
////                        "Heading 3",
////                        "Description 3",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        4,
////                        "Heading 4",
////                        "Description 4",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        5,
////                        "Heading 5",
////                        "Description 5",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        1,
////                        "Heading 1",
////                        "Description 1",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        2,
////                        "Heading 2",
////                        "Description 2",
////                        R.drawable.ic_android_black_24dp,
////                        true));
////
////        allDataList.add(
////                new Card(
////                        3,
////                        "Heading 3",
////                        "Description 3",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        4,
////                        "Heading 4",
////                        "Description 4",
////                        R.drawable.ic_android_black_24dp,
////                        false));
////
////        allDataList.add(
////                new Card(
////                        5,
////                        "Heading 5",
////                        "Description 5",
////                        R.drawable.ic_android_black_24dp,
////                        false));
//
//
//        return allDataList;
//    }

//    public List<Card> getAllChosenFromListOfAllData() {
//        chosenList = (List<Card>) checkAndAddForChosenList();
//        return chosenList;
//    }

    public  Set<Card> checkAndAddForChosenList(){
        Set<Card> chosenSet = new HashSet<>();
         for (Card c : allDataList){
             if (c.isEnabled()){
                 chosenSet.add(c);
             }
         }
         return chosenSet;
    }

    public List<Card> getChosenList() {
        return chosenList;
    }

    public List<Card> getAllDataList() {
        return allDataList;
    }

    public void setAllDataList(List<Card> allDataList) {
        this.allDataList = allDataList;
    }

    public void removeFavItem(Card card) {
        chosenList.remove(card);
    }

    public void addFavItem(Card card) {
        chosenList.add(card);
    }
}
