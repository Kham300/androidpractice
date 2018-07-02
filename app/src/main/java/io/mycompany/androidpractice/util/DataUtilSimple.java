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
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                "ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                                "Duis aute irure dolor in reprehenderit in voluptate velit " +
                                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint" +
                                " occaecat cupidatat non proident, sunt in culpa qui officia " +
                                "deserunt mollit anim id est laborum",
                        R.drawable.ic_android_black_24dp,
                        true));

        allListData.add(
                new Card(
                        3,
                        "Heading 3",
                        "Description 3",
                        R.drawable.ic_android_black_24dp,
                        true));

        allListData.add(
                new Card(
                        4,
                        "Heading 11",
                        "Description 1",
                        R.drawable.ic_android_black_24dp,
                        false));

        allListData.add(
                new Card(
                        5,
                        "Heading 22",
                        "Description 2",
                        R.drawable.ic_android_black_24dp,
                        true));

        allListData.add(
                new Card(
                        6,
                        "Heading 33",
                        "Description 33",
                        R.drawable.ic_android_black_24dp,
                        false));
        allListData.add(
                new Card(
                        7,
                        "Heading 22212",
                        "Description 2342",
                        R.drawable.ic_android_black_24dp,
                        true));

        allListData.add(
                new Card(
                        8,
                        "Heading 3333",
                        "Description 3333",
                        R.drawable.ic_android_black_24dp,
                        true));

        allListData.add(
                new Card(
                        9,
                        "Heading 111",
                        "Description 111",
                        R.drawable.ic_android_black_24dp,
                        false));

        allListData.add(
                new Card(
                        10,
                        "Heading 222",
                        "Description 222",
                        R.drawable.ic_android_black_24dp,
                        true));

        allListData.add(
                new Card(
                        11,
                        "Heading 333",
                        "Description 333",
                        R.drawable.ic_android_black_24dp,
                        false));
    }

    static {
        for (Card c : allListData){
            if (c.isEnabled()){
                addFavItem(c);
            }
        }
        }

    public static void removeAllItems() {
        favoriteList.clear();
    }

    public static void removeFavItem(Card card) {
        favoriteList.remove(card);
    }

    public static void addFavItem(Card card) {
        if (!favoriteList.contains(card)) {
            favoriteList.add(card);
        }
    }
}
