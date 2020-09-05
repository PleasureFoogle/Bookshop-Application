package yesgroup.myapplication.domain.dtos.importJson;

import com.google.gson.annotations.Expose;
import yesgroup.myapplication.domain.entities.Author;

public class JsonBookDto {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String author;
    @Expose
    private int numberOfPages;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}

