package io.mycompany.androidpractice.model;

import android.widget.ImageButton;
import android.widget.ImageView;

public class Card {

    private int id;
    private String heading, description;
    private int image;
    private boolean isEnabled;


    public Card(int id, String heading, String description, int image, boolean isEnabled) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.image = image;
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

}
