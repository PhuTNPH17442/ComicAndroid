package com.example.comic.Model;

import java.io.Serializable;

public class Comic implements Serializable {
    public int idComic;
    public String name;
    public String year;
    public String author;
    public String avatar;
    public String content;

    public Comic() {
    }

    public Comic(int idComic, String name, String year, String author, String avatar, String content) {
        this.idComic = idComic;
        this.name = name;
        this.year = year;
        this.author = author;
        this.avatar = avatar;
        this.content = content;
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
