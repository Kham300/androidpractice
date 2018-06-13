package io.mycompany.androidpractice.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;

public class DataUtil {

    //TODO data is duplicates????
    static List<Card> allDataList = new ArrayList<>();
    static Set<Card> chosenSet = new HashSet<>();

    public static List<Card> getAllData(){
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

//        allDataList.add(
//                new Card(
//                        4,
//                        "Heading 4",
//                        "Description 4",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        5,
//                        "Heading 5",
//                        "Description 5",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        1,
//                        "Heading 1",
//                        "Description 1",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        2,
//                        "Heading 2",
//                        "Description 2",
//                        R.drawable.ic_android_black_24dp,
//                        true));
//
//        allDataList.add(
//                new Card(
//                        3,
//                        "Heading 3",
//                        "Description 3",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        4,
//                        "Heading 4",
//                        "Description 4",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        5,
//                        "Heading 5",
//                        "Description 5",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        1,
//                        "Heading 1",
//                        "Description 1",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        2,
//                        "Heading 2",
//                        "Description 2",
//                        R.drawable.ic_android_black_24dp,
//                        true));
//
//        allDataList.add(
//                new Card(
//                        3,
//                        "Heading 3",
//                        "Description 3",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        4,
//                        "Heading 4",
//                        "Description 4",
//                        R.drawable.ic_android_black_24dp,
//                        false));
//
//        allDataList.add(
//                new Card(
//                        5,
//                        "Heading 5",
//                        "Description 5",
//                        R.drawable.ic_android_black_24dp,
//                        false));


        return allDataList;
    }

    public static List<Card> getAllChosenList() {

        chosenSet = checkAndAddForChosenList();

        return new ArrayList<>(chosenSet);
    }

    public static Set<Card> checkAndAddForChosenList(){
        ArrayList<Card> list = new ArrayList<>(getAllData());
         for (Card c : list){
             if (c.isEnabled()){
                 chosenSet.add(c);
             }
         }

         return chosenSet;
    }

    public static void refreshChosenList(){
        ArrayList<Card> list = new ArrayList<>(getAllData());
         for (Card c : list){
             if (c.isEnabled()){
                 chosenSet.add(c);
             }
         }
    }
}
