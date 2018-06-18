package io.mycompany.androidpractice.util;

import java.util.ArrayList;
import java.util.List;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;

public class DataUtilSimple {

    public static List<Card> allListData = new ArrayList<>();
    public static List<Card> favoriteList = new ArrayList<>();

    static {

        allListData.add(
                new Card(
                        1,
                        "Heading 1",
                        "Description 1",
                        R.drawable.ic_android_black_24dp,
                        false));

        allListData.add(
                new Card(
                        2,
                        "Heading 2",
                        "Description 2",
                        R.drawable.ic_android_black_24dp,
                        true));

        allListData.add(
                new Card(
                        3,
                        "Heading 3",
                        "Description 3",
                        R.drawable.ic_android_black_24dp,
                        false));

        }



    public static void removeAllItems() {
        favoriteList.clear();
    }

//    public static List<Card> initFavItems(){
//        List<Card> list = new ArrayList<>();
//        for (Card c : allListData){
//            if (c.isEnabled()) {
//                list.add(c);
//            }
//        }
//        return list;
//    }

    public static void removeFavItem(Card card) {
        favoriteList.remove(card);
    }

    public static void addFavItem(Card card) {
        if (!favoriteList.contains(card)) {
            favoriteList.add(card);
        }
    }
}
