package yesgroup.myapplication.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bookshops")
public class Bookshop extends BaseEntity {
    private int id;
    private List<Book> booksInStock;
    private String name;
    private int distanceInKilometers;

    @ManyToMany
    public List<Book> getBooksInStock() {
        return booksInStock;
    }

    public void setBooksInStock(List<Book> booksInStock) {
        this.booksInStock = booksInStock;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "distance_in_kilometers")
    public int getDistanceInKilometers() {
        return distanceInKilometers;
    }

    public void setDistanceInKilometers(int distanceInKilometers) {
        this.distanceInKilometers = distanceInKilometers;
    }
}
