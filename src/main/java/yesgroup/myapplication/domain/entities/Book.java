package yesgroup.myapplication.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    public Book() {
    }

    private int id;
    private Author author;
    private String name;
    private int numberOfPages;
    private List<Bookshop> bookshops;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "author_id")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "numberOfPages")

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @ManyToMany(mappedBy = "booksInStock")
    public List<Bookshop> getBookshops() {
        return bookshops;
    }

    public void setBookshops(List<Bookshop> bookshops) {
        this.bookshops = bookshops;
    }
}

