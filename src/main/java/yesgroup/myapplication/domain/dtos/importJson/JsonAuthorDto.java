package yesgroup.myapplication.domain.dtos.importJson;

import com.google.gson.annotations.Expose;
import yesgroup.myapplication.domain.entities.Book;

import java.util.List;

public class JsonAuthorDto {
    @Expose
    private int id;
    @Expose
    private int age;
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

