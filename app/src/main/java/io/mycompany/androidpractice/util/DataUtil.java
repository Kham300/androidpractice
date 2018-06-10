package io.mycompany.androidpractice.util;

import java.util.ArrayList;
import java.util.List;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;

public class DataUtil {

    public static List<Card> getData(){
        List<Card> listData = new ArrayList<>();

        listData.add(
                new Card(
                        1,
                        "Heading 1",
                        "Description 1",
                        R.drawable.ic_android_black_24dp,
                        false));

        listData.add(
                new Card(
                        2,
                        "Heading 2",
                        "Description 2",
                        R.drawable.ic_android_black_24dp,
                        true));

        listData.add(
                new Card(
                        3,
                        "Heading 3",
                        "Description 3",
                        R.drawable.ic_android_black_24dp,
                        false));

        listData.add(
                new Card(
                        4,
                        "Heading 4",
                        "Description 4",
                        R.drawable.ic_android_black_24dp,
                        false));

        listData.add(
                new Card(
                        5,
                        "Heading 5",
                        "Description 5",
                        R.drawable.ic_android_black_24dp,
                        false));


        return listData;
    }
}
