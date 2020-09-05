package yesgroup.myapplication.domain.dtos.importJson;

import com.google.gson.annotations.Expose;
import yesgroup.myapplication.domain.entities.Book;

import java.util.List;

public class JsonBookshopDto {
    @Expose
    private int id;
    @Expose
    public String name;
    @Expose
    private int distanceInKilometers;
    @Expose
    private String booksInStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistanceInKilometers() {
        return distanceInKilometers;
    }

    public void setDistanceInKilometers(int distanceInKilometers) {
        this.distanceInKilometers = distanceInKilometers;
    }

    public String getBooksInStock() {
        return booksInStock;
    }

    public void setBooksInStock(String books) {
        this.booksInStock = books;
    }
}
