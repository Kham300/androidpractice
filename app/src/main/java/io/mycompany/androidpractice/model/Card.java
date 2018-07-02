package io.mycompany.androidpractice.model;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import io.mycompany.androidpractice.R;

public class Card {

    private static final AtomicInteger count = new AtomicInteger(12);
    private int id;
    private String heading, description;
    private int image;
    private boolean isEnabled;

    public Card (){
    }


    public Card(int id, String heading, String description, int image, boolean isEnabled) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.image = image;
        this.isEnabled = isEnabled;
    }

    public Card(String heading, String description, boolean isEnabled){
        id = count.incrementAndGet();
        this.heading = heading;
        this.description = description;
        this.image = R.drawable.ic_android_black_24dp;
        this.isEnabled = isEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean bol){
        this.isEnabled = bol;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
