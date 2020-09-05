package yesgroup.myapplication.domain.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "authors")
public class Author extends BaseEntity {
    public Author() {
    }

    private int id;
    private String name;
    private int age;
    private List<Book> books;


    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "name")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "author")
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
